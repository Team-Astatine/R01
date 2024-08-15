package teamzesa.event.Enhance.PlayerInteraction;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.DataBase.enhance.EnhanceItemBuilder;
import teamzesa.DataBase.entity.EnhanceItem;
import teamzesa.event.Enhance.GeneratingEnhanceItem;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.LongRangeWeaponMap;
import teamzesa.util.Enum.Enhance.ProtectScroll;
import teamzesa.util.Enum.Enhance.Scroll;
import teamzesa.util.Enum.Enhance.ShortRangeWeaponMap;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.HashSet;

import static teamzesa.command.EnhanceDialog.ENHANCE_DIALOG;

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
        if (BooleanUtils.isFalse(event.getInventory().equals(ENHANCE_DIALOG))) {
            return;
        }

//        Add Allowed Item
        if (this.enhanceItem != null && this.scrollStuff != null) {
            for (ShortRangeWeaponMap weapon : ShortRangeWeaponMap.values())
                this.allowedItem.add(weapon.getMaterial());

            for (LongRangeWeaponMap weapon : LongRangeWeaponMap.values())
                this.allowedItem.add(weapon.getMaterial());

            for (Scroll scroll : Scroll.values())
                this.allowedScroll.add(scroll.getMaterial());

            for (ProtectScroll scroll : ProtectScroll.values())
                this.allowedScroll.add(scroll.getMaterial());
        }

        Inventory playerOpenInv = this.event.getClickedInventory();
        if (ObjectUtils.notEqual(playerOpenInv, null) && playerOpenInv.getType().equals(InventoryType.DROPPER)) {
            switch (this.event.getSlot()) {
                case 0, 1, 2 -> {
                    this.event.setCancelled(true);
                }

                case 6 -> {
                    this.event.setCancelled(true);
                    event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                    event.getWhoClicked().sendMessage(createLinkComponentExchanger(
                            ConfigIOHandler.getConfigIOHandler().getDiscordInvite(),
                            ConfigIOHandler.getConfigIOHandler().getDiscordConfig(),
                            ColorMap.DISCORD_COLOR));
                }

                case 7 -> {
                    if (isAllowedEnhanceItem()) {
                        EnhanceItem enhanceItemObj = new EnhanceItemBuilder()
                                .enhancePlayer((Player) this.event.getWhoClicked())
                                .enhanceItem(this.enhanceItem)
                                .enhanceScroll(this.scrollStuff)
                                .protectScroll(this.protectScroll)
                                .build();

                        new GeneratingEnhanceItem(enhanceItemObj);
                    }
                    this.event.setCancelled(true);
                }

                case 8 -> {
                    this.event.setCancelled(true);
                    event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                    event.getWhoClicked().sendMessage(createLinkComponentExchanger(
                            ConfigIOHandler.getConfigIOHandler().getServerGuideNotion(),
                            ConfigIOHandler.getConfigIOHandler().getNotionConfig(),
                            ColorMap.NOTION_COLOR));
                }
            }
        }
    }

    private boolean isAllowedEnhanceItem() {
        String comment = "";

        if (this.enhanceItem == null || this.enhanceItem.isEmpty())
            comment = "무기를 올려주세요.";

        else if (this.scrollStuff == null || this.scrollStuff.isEmpty())
            comment = "강화 주문서가 부족합니다.";

        else if (BooleanUtils.isFalse(this.allowedItem.contains(this.enhanceItem.getType())))
            comment = "허용된 아이템을 넣어주세요.";

        else if (BooleanUtils.isFalse(this.allowedScroll.contains(this.scrollStuff.getType())))
            comment = "허용된 주문서를 넣어주세요";

        else if (this.protectScroll != null && BooleanUtils.isFalse(this.allowedScroll.contains(this.protectScroll.getType())))
            comment = "허용된 주문서를 넣어주세요";

        if (BooleanUtils.isFalse(comment.isBlank()))
            playerSendMsgComponentExchanger(this.ownerPlayer, comment, ColorMap.RED);

        return comment.isBlank();
    }
}
