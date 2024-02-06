package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserController;


public class GodModeTridentHitEvent implements EventRegister {
    private final ProjectileHitEvent event;

    public GodModeTridentHitEvent(ProjectileHitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {

    }

    @Override
    public void execute() {
        if (!(this.event.getEntity().getShooter() instanceof Player shooter))
            return;

        if (!getUser(shooter).godMode())
            return;

        if (!(this.event.getEntity() instanceof Trident trident))
            return;

        Runnable tridentTask = () -> {
            trident.setLoyaltyLevel(3);
            trident.setGlowing(true);

            Location loc = trident.getLocation();
            loc.getWorld().strikeLightning(loc);
            loc.getWorld().createExplosion(loc, 4F, true);
            trident.getWorld().playSound(loc, Sound.ITEM_TRIDENT_THUNDER, 5F, 5F);
        };

        tridentTask.run();
//        if (this.event.getHitBlock() != null && this.event.getHitBlock().getType() != Material.BEDROCK)
//            tridentTask.run();
    }

    private User getUser(Player player) {
        return new UserController().readUser(player);
    }
}
