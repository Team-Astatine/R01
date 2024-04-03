package teamzesa.event.Enhance;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserController;

import static teamzesa.command.EnhanceStuff.EXECUTE_STUFF_DATA;
import static teamzesa.command.EnhanceStuff.PANEL_STUFF_CUSTOM_DATA;

public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
    private User targetUser;
    private Player ownerPlayer;
    private ItemStack currentStuff;
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
        this.targetUser = new UserController().readUser(this.ownerPlayer);
    }

    private boolean interactingInfoItem(int modelData) {
        boolean valid1 = this.event.getInventory().getType() == InventoryType.DROPPER;
        boolean valid2 = this.currentStuff != null;
        boolean valid3 = valid2 && this.currentStuff.getItemMeta() != null;
        boolean valid4 = valid2 && this.currentStuff.hasCustomModelData();
        boolean valid5 = valid4 && this.currentStuff.getCustomModelData() == modelData;
        return valid1 && valid2 && valid3 && valid4 && valid5;
    }

    @Override
    public void execute() {
        System.out.println("execute 1");
        if (interactingInfoItem(PANEL_STUFF_CUSTOM_DATA)) {
            this.event.setCancelled(true);
            return;
        }


        System.out.println("execute 2");
        if (interactingInfoItem(EXECUTE_STUFF_DATA))
            System.out.println(1);

//        ItemStack resultStuff = new Algorithm()
//                .addWeaponStuff(this.currentStuff)
//                .executeEnhance();
    }
}
