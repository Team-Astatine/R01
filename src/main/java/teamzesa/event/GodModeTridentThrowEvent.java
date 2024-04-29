package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;
import teamzesa.entity.User;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.userHandler.UserController;

public class GodModeTridentThrowEvent implements EventRegister {
    private final ProjectileLaunchEvent event;

    public GodModeTridentThrowEvent(ProjectileLaunchEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        if (!(this.event.getEntity().getShooter() instanceof Player shooter))
            return;

        if (!getUser(shooter).isGodMode())
            return;

        Vector projectile = this.event.getEntity().getVelocity(); //현재속도 get
        this.event.getEntity().setVelocity(projectile.multiply(3)); //속도 set
    }

    private User getUser(Player player) {
        return new UserController().readUser(player);
    }
}
