package teamzesa.Event.Enhance.PlayerInteraction.UserInterface;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.Data.DataIO.Config.ConfigIOHandler;
import teamzesa.Data.Function.Enhance.EnhanceInventoryManager;
import teamzesa.Data.Function.Enhance.EnhanceItemBuilder;
import teamzesa.Data.Function.Enhance.EnhanceItem;
import teamzesa.Enumeration.Enhance.Items.Armour.ArmourList;
import teamzesa.Enumeration.Enhance.Items.Scroll.ProtectScrollList;
import teamzesa.Enumeration.Enhance.Items.Scroll.ScrollList;
import teamzesa.Enumeration.Enhance.Items.Weapon.LongRange;
import teamzesa.Enumeration.Enhance.Items.Weapon.ShortRange;
import teamzesa.Event.Enhance.GeneratingEnhanceItem;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.UserUIGenerator.InventoryUIGenerator;
import teamzesa.Util.StringComponentExchanger;

import java.util.Arrays;
import java.util.HashSet;


public class EnhanceUIClickEvent extends StringComponentExchanger implements EventRegister {
    private final InventoryClickEvent event;
    private Player ownerPlayer;
    private Inventory enhanceInventory;
    private ItemStack currentItem;
    private ItemStack enhanceItem;
    private ItemStack scrollStuff;
    private ItemStack protectScroll;
    private HashSet<Material> allowedItem;
    private HashSet<Material> allowedScroll;

    public EnhanceUIClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentItem = this.event.getCurrentItem();
        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.enhanceInventory = EnhanceInventoryManager.getEnhanceInventoryManager().get(this.ownerPlayer.getUniqueId());

        this.enhanceItem = this.event.getView().getItem(3);
        this.scrollStuff = this.event.getView().getItem(4);
        this.protectScroll = this.event.getView().getItem(5);

        this.allowedItem = new HashSet<>();
        this.allowedScroll = new HashSet<>();
    }

    /**
     * 인벤토리를 열고 닫음에 있어서 인벤토리 주인을 찾습니다.
     * Click Inventory 생성은 {@link InventoryUIGenerator}를 참고해주세요.
     * Inventory UUID 를 고정하기 위해 {@link EnhanceInventoryManager}를 사용합니다.
     * 인벤토리 생성, 등록이 완료되면 플레이어가 강화 이벤트를 발생시키는지 확인합니다.
     * 강화는 {@link GeneratingEnhanceItem} 를 참조하세요.
     */
    @Override
    public void execute() {
        if (BooleanUtils.isFalse(this.event.getInventory().equals(this.enhanceInventory))) {
            return;
        }

//        Add Allowed Item
        if (ObjectUtils.allNotNull(this.enhanceItem, this.scrollStuff)) {
            Arrays.stream(ShortRange.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));
            Arrays.stream(LongRange.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));
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
                    event.getWhoClicked().sendMessage(createLinkComponentExchanger(
                            ConfigIOHandler.getConfigIOHandler().getDiscordInvite(),
                            ConfigIOHandler.getConfigIOHandler().getDiscordConfig(),
                            ColorType.DISCORD_COLOR
                    ));
                }

                case 7 -> {
                    if (isAllowedEnhanceItem()) {
                        EnhanceItem enhanceItemObj = new EnhanceItemBuilder()
                                .enhancePlayer((Player) this.event.getWhoClicked())
                                .enhanceItem(this.enhanceItem)
                                .enhanceScroll(this.scrollStuff)
                                .protectScroll(this.protectScroll)
                                .generating();

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
                            ColorType.NOTION_COLOR
                    ));
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
            playerSendMsgComponentExchanger(this.ownerPlayer, comment, ColorType.RED);

        return comment.isBlank();
    }
}
