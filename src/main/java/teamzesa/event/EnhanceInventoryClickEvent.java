package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.w3c.dom.ls.LSOutput;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserController;

public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
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
        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.targetUser = new UserController().readUser(this.ownerPlayer);
        this.playerInventory = this.event.getClickedInventory();
        this.currentOpeningContainerInventory = this.event.getInventory();
    }

    @Override
    public void execute() {
//        강화가능한 인벤토리인지 확인
        System.out.println(1);
        if (this.currentOpeningContainerInventory.getSize() != 2 * 9)
            return;

        System.out.println(2);
        if (this.currentOpeningContainerInventory.getType() != InventoryType.CHEST)
            return;

        System.out.println(3);
        
    }
}
