package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserController;

public class EnhanceInventoryClickEvent extends StringComponentExchanger implements EventRegister {
    private User targetUser;
    private Player targetPlayer;
    private final InventoryClickEvent event;

    public EnhanceInventoryClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.targetPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.targetUser = new UserController().readUser(this.targetPlayer);
    }

    @Override
    public void execute() {

    }
}
