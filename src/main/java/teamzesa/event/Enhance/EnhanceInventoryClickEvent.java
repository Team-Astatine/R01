package teamzesa.event.Enhance;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.WeaponMap;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.HashSet;

import static teamzesa.command.EnhanceStuff.EXECUTE_STUFF_DATA;
import static teamzesa.command.EnhanceStuff.PANEL_STUFF_CUSTOM_DATA;

public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
    private Player ownerPlayer;
    private ItemStack currentStuff;
    private ItemStack targetStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScroll;

    private HashSet<ItemStack> allowedItem;
    private HashSet<ItemStack> allowedScroll;
    private HashSet<ItemStack> allowedProtectScroll;

    private final InventoryClickEvent event;

    public EnhanceInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentStuff = this.event.getCurrentItem();
        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;

        this.targetStuff = this.event.getView().getItem(3);
        this.scrollStuff = this.event.getView().getItem(4);
        this.protectScroll = this.event.getView().getItem(5);

        this.allowedItem = new HashSet<>();
        for (WeaponMap weaponMap : WeaponMap.values())
            this.allowedItem.add(new ItemStack(weaponMap.getMaterial()));

//        methodImplement
        this.allowedScroll = new HashSet<>();


        this.allowedProtectScroll = new HashSet<>();
    }

    @Override
    public void execute() {
        if (isInteractingInfoItemValidation(PANEL_STUFF_CUSTOM_DATA)) {
            this.event.setCancelled(true);
            return;
        }

        if (isInteractingInfoItemValidation(EXECUTE_STUFF_DATA) && isStuffScrollValid()) {
            new EnhanceResultStuffGenerator()
                    .addWeaponOwner((Player)this.event.getWhoClicked())
                    .addWeaponStuff(this.targetStuff)
                    .addScrollStuff(this.scrollStuff)
                    .addProtectScrollStuff(this.protectScroll)
                    .executeEnhance();

            this.event.setCancelled(true);
        }
    }

    private boolean isInteractingInfoItemValidation(int modelData) {
        boolean valid1 = this.event.getInventory().getType() == InventoryType.DROPPER;
        boolean valid2 = this.currentStuff != null;
        boolean valid3 = valid2 && this.currentStuff.getItemMeta() != null;
        boolean valid4 = valid2 && this.currentStuff.hasCustomModelData();
        boolean valid5 = valid4 && this.currentStuff.getCustomModelData() == modelData;
        return valid1 && valid2 && valid3 && valid4 && valid5;
    }

    private boolean isStuffScrollValid() {
        if (this.targetStuff == null) {
            playerSendMsgComponentExchanger(this.ownerPlayer, "무기를 올려주세요.", ColorMap.RED);
            this.event.setCancelled(true);
            return false;
        }

        if (this.scrollStuff == null) {
            playerSendMsgComponentExchanger(this.ownerPlayer, "강화 주문서가 부족합니다.", ColorMap.RED);
            this.event.setCancelled(true);
            return false;
        }
        return true;
    }
}
