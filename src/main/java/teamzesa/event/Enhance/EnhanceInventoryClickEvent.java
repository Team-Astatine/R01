package teamzesa.event.Enhance;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;

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

    private boolean valid() {
        System.out.println(1);
        if (this.currentStuff.getType() == Material.AIR)
            return true;

        System.out.println(2);
        if (this.currentStuff.getItemMeta() == null)
            return false;

        System.out.println(3);
        if (this.currentStuff.hasCustomModelData())
            return false;

        System.out.println(4);
        if (this.currentStuff.getCustomModelData() != PANEL_STUFF_CUSTOM_DATA)
            return true;

        return false;
    }

    @Override
    public void execute() {
        System.out.println("valid 1");
        if (valid()) {
            System.out.println("valid1 return");
            this.event.setCancelled(true);
            return;
        }



        System.out.println(2);



        ItemStack resultStuff = new Algorithm()
                .addWeaponStuff(this.currentStuff)
                .executeEnhance();
    }
}
