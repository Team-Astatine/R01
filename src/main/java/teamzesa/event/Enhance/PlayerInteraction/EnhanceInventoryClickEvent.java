package teamzesa.event.Enhance.PlayerInteraction;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.DataBase.enhance.EnhanceInventoryHandler;
import teamzesa.DataBase.enhance.EnhanceItemBuilder;
import teamzesa.DataBase.entity.Enhance.EnhanceItem;
import teamzesa.event.Enhance.GeneratingEnhanceItem;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.Enhance.*;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.Arrays;
import java.util.HashSet;


public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
    private final InventoryClickEvent event;
    private Player ownerPlayer;
    private Inventory enhanceInventory;
    private ItemStack currentItem;
    private ItemStack enhanceItem;
    private ItemStack scrollStuff;
    private ItemStack protectScroll;
    private HashSet<Material> allowedItem;
    private HashSet<Material> allowedScroll;

    public EnhanceInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentItem = this.event.getCurrentItem();
        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.enhanceInventory = EnhanceInventoryHandler.getEnhanceInventoryHandler().get(this.ownerPlayer.getUniqueId());

        this.enhanceItem = this.event.getView().getItem(3);
        this.scrollStuff = this.event.getView().getItem(4);
        this.protectScroll = this.event.getView().getItem(5);

        this.allowedItem = new HashSet<>();
        this.allowedScroll = new HashSet<>();
    }

    @Override
    public void execute() {
        if (BooleanUtils.isFalse(this.event.getInventory().equals(this.enhanceInventory))) {
            return;
        }

//        Add Allowed Item
        if (ObjectUtils.allNotNull(this.enhanceItem, this.scrollStuff)) {
            Arrays.stream(ShortRangeWeaponMap.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));
            Arrays.stream(LongRangeWeaponMap.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));
            Arrays.stream(ArmourList.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));

            Arrays.stream(ScrollList.values()).forEach(i -> this.allowedScroll.add(i.getMaterial()));
            Arrays.stream(ProtectScrollList.values()).forEach(i -> this.allowedScroll.add(i.getMaterial()));
        }

        Inventory playerOpenInv = this.event.getClickedInventory();
        if (ObjectUtils.isNotEmpty(playerOpenInv) && playerOpenInv.getType().equals(InventoryType.DROPPER)) {
            switch (this.event.getSlot()) {
                case 0, 1, 2 -> {
                    this.event.setCancelled(true);
                }

                case 6 -> {
                    this.event.setCancelled(true);
                    event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                    event.getWhoClicked().sendMessage(createLinkComponentExchanger(ConfigIOHandler.getConfigIOHandler().getDiscordInvite(), ConfigIOHandler.getConfigIOHandler().getDiscordConfig(), ColorList.DISCORD_COLOR));
                }

                case 7 -> {
                    if (isAllowedEnhanceItem()) {
                        EnhanceItem enhanceItemObj = new EnhanceItemBuilder().enhancePlayer((Player) this.event.getWhoClicked()).enhanceItem(this.enhanceItem).enhanceScroll(this.scrollStuff).protectScroll(this.protectScroll).build();

                        new GeneratingEnhanceItem(enhanceItemObj);
                    }
                    this.event.setCancelled(true);
                }

                case 8 -> {
                    this.event.setCancelled(true);
                    event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                    event.getWhoClicked().sendMessage(createLinkComponentExchanger(ConfigIOHandler.getConfigIOHandler().getServerGuideNotion(), ConfigIOHandler.getConfigIOHandler().getNotionConfig(), ColorList.NOTION_COLOR));
                }
            }
        }
    }

    private boolean isAllowedEnhanceItem() {
        String comment = "";

        if (ObjectUtils.isEmpty(this.enhanceItem) || this.enhanceItem.isEmpty()) comment = "무기를 올려주세요.";

        else if (ObjectUtils.isEmpty(this.scrollStuff) || this.scrollStuff.isEmpty()) comment = "강화 주문서가 부족합니다.";

        else if (BooleanUtils.isFalse(this.allowedItem.contains(this.enhanceItem.getType())))
            comment = "허용된 아이템을 넣어주세요.";

        else if (BooleanUtils.isFalse(this.allowedScroll.contains(this.scrollStuff.getType())))
            comment = "허용된 주문서를 넣어주세요";

        else if (ObjectUtils.allNotNull(this.protectScroll) && BooleanUtils.isFalse(this.allowedScroll.contains(this.protectScroll.getType())))
            comment = "허용된 주문서를 넣어주세요";

        if (BooleanUtils.isFalse(comment.isBlank()))
            playerSendMsgComponentExchanger(this.ownerPlayer, comment, ColorList.RED);

        return comment.isBlank();
    }
}
