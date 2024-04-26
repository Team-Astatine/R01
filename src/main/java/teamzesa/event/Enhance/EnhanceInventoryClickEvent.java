package teamzesa.event.Enhance;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.ScrollMap;
import teamzesa.util.Enum.WeaponMap;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.HashSet;

import static teamzesa.command.EnhanceStuff.EXECUTE_STUFF_DATA;
import static teamzesa.command.EnhanceStuff.PANEL_STUFF_CUSTOM_DATA;

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
        for (WeaponMap weaponMap : WeaponMap.values())
            this.allowedItem.add(weaponMap.getMaterial());

        this.allowedScroll = new HashSet<>();
        for (ScrollMap scrollMap : ScrollMap.values())
            this.allowedScroll.add(scrollMap.getMaterial());
    }

    @Override
    public void execute() {
        if (isInteractingInfoItemValidation(PANEL_STUFF_CUSTOM_DATA)) {
            this.event.setCancelled(true);
            return;
        }

        if (isInteractingInfoItemValidation(EXECUTE_STUFF_DATA)) {
            if (isEnhanceInvStuffValid())
                new EnhanceResultStuffGenerator()
                        .addWeaponOwner((Player)this.event.getWhoClicked())
                        .addWeaponStuff(this.enhanceItem)
                        .addScrollStuff(this.scrollStuff)
                        .addProtectScrollStuff(this.protectScroll)
                        .executeEnhance();

            this.event.setCancelled(true);
        }
    }

    private boolean isInteractingInfoItemValidation(int modelData) {
        boolean valid1 = this.event.getInventory().getType() == InventoryType.DROPPER;
        boolean valid2 = this.currentItem != null;
        boolean valid3 = valid2 && this.currentItem.getItemMeta() != null;
        boolean valid4 = valid2 && this.currentItem.hasCustomModelData();
        boolean valid5 = valid4 && this.currentItem.getCustomModelData() == modelData;
        return valid1 && valid2 && valid3 && valid4 && valid5;
    }

    private boolean isEnhanceInvStuffValid() {
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
