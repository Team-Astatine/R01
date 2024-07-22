package teamzesa.event.Enhance.Dialog;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.enhance.EnhanceItemBuilder;
import teamzesa.DataBase.entity.EnhanceItem;
import teamzesa.event.Enhance.GeneratingEnhanceItem;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.LongRangeWeaponMap;
import teamzesa.util.Enum.Enhance.ScrollMap;
import teamzesa.util.Enum.Enhance.ShortRangeWeaponMap;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.HashSet;

import static teamzesa.command.EnhanceDialog.EXECUTE_STUFF_DATA;
import static teamzesa.command.EnhanceDialog.PANEL_STUFF_CUSTOM_DATA;

public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
    private Player ownerPlayer;
    private ItemStack currentItem;
    private ItemStack enhanceItem;
    private ItemStack scrollStuff;
    private ItemStack protectScroll;

    private HashSet<Material> allowedItem;
    private HashSet<Material> allowedScroll;

    private final InventoryClickEvent event;

    public EnhanceInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentItem = this.event.getCurrentItem();
        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;

        this.enhanceItem = this.event.getView().getItem(3);
        this.scrollStuff = this.event.getView().getItem(4);
        this.protectScroll = this.event.getView().getItem(5);

        this.allowedItem = new HashSet<>();
        this.allowedScroll = new HashSet<>();
    }

    @Override
    public void execute() {
        if (isAllowedInteractItem(PANEL_STUFF_CUSTOM_DATA)) {
            this.event.setCancelled(true);
            return;
        }

//        Add Allowed Item
        if (this.enhanceItem != null && this.scrollStuff != null) {
            for (ShortRangeWeaponMap shortRangeWeaponMap : ShortRangeWeaponMap.values())
                this.allowedItem.add(shortRangeWeaponMap.getMaterial());

            for (LongRangeWeaponMap longRangeWeaponMap : LongRangeWeaponMap.values())
                this.allowedItem.add(longRangeWeaponMap.getMaterial());

            for (ScrollMap scrollMap : ScrollMap.values())
                this.allowedScroll.add(scrollMap.getMaterial());
        }

        if (isAllowedInteractItem(EXECUTE_STUFF_DATA)) {
            if (isAllowedEnhanceItem()) {
                EnhanceItem enhanceItemObj = new EnhanceItemBuilder()
                        .enhancePlayer((Player)this.event.getWhoClicked())
                        .enhanceItem(this.enhanceItem)
                        .enhanceScroll(this.scrollStuff)
                        .protectScroll(this.protectScroll)
                        .build();

                new GeneratingEnhanceItem(enhanceItemObj);
            }
            this.event.setCancelled(true);
        }
    }

    private boolean isAllowedInteractItem(int allowedCustomModelData) {
        boolean valid1 = false, valid2 = false, valid3 = false, valid4 = false, valid5 = false;

        valid1 = this.event.getInventory().getType() == InventoryType.DROPPER;
        valid2 = this.currentItem != null;
        valid3 = valid2 && this.currentItem.hasItemMeta();

        if (valid3 && this.currentItem.hasItemMeta())
            valid4 = this.currentItem.getItemMeta().hasCustomModelData();

        if (valid4 && this.currentItem.getItemMeta().hasCustomModelData())
            valid5 = this.currentItem.getItemMeta().getCustomModelData() == allowedCustomModelData;

        return valid1 && valid2 && valid3 && valid4 && valid5;
    }

    private boolean isAllowedEnhanceItem() {
        String comment = "";
        if (this.enhanceItem == null || this.enhanceItem.isEmpty())
            comment = "무기를 올려주세요.";

        else if (this.scrollStuff == null || this.scrollStuff.isEmpty())
            comment = "강화 주문서가 부족합니다.";

        else if (!this.allowedItem.contains(this.enhanceItem.getType()))
            comment = "허용된 아이템을 넣어주세요.";

        else if (!this.allowedScroll.contains(this.scrollStuff.getType()))
            comment = "허용된 주문서를 넣어주세요";

        else if (this.protectScroll != null && !this.allowedScroll.contains(this.protectScroll.getType()))
            comment = "허용된 주문서를 넣어주세요";

        if (!comment.isBlank())
            playerSendMsgComponentExchanger(this.ownerPlayer, comment, ColorMap.RED);
        return comment.isBlank();
    }
}
