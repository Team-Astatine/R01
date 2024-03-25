package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserController;

import static teamzesa.command.EnhanceStuff.PANEL_STUFF_CUSTOM_DATA;

public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
    private ItemStack currentStuff;
    private User targetUser;
    private Player ownerPlayer;
    private Inventory playerInventory;
    private Inventory currentOpeningContainerInventory;
    private final InventoryClickEvent event;

    public EnhanceInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentStuff = this.event.getCurrentItem();
        this.playerInventory = this.event.getClickedInventory();
        this.currentOpeningContainerInventory = this.event.getInventory();

        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.targetUser = new UserController().readUser(this.ownerPlayer);
    }

    public boolean valid() {
        System.out.println("valid 1");
        if (this.currentStuff == null)
            return false;

        System.out.println("valid 2");
        if (this.currentStuff.hasCustomModelData())
            return false;

        System.out.println("valid 3");
        if (this.currentStuff.getCustomModelData() == PANEL_STUFF_CUSTOM_DATA)
            return true;

        return false;
    }

    @Override
    public void execute() {
        System.out.println(1);
        boolean test = valid();
        System.out.println(test);
        if (test)
            this.event.setCancelled(true);

        System.out.println(2);
    }
}
