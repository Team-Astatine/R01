package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserController;


public class GodEvent implements Listener {
    Trident trident;
    Runnable tridentTask;
    @EventHandler
    public void moderatorEvent(@NotNull ProjectileHitEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player shooter))
            return;

        if (!getUser(shooter).godMode())
            return;

        if (e.getEntity() instanceof Trident trident) {
            this.trident = trident;
            this.tridentTask = () -> {
                trident.setLoyaltyLevel(3);
                trident.setGlowing(true);

                Location loc = trident.getLocation();
                loc.getWorld().strikeLightning(loc);
                loc.getWorld().createExplosion(loc,4F,true);
                trident.getWorld().playSound(loc, Sound.ITEM_TRIDENT_THUNDER, 5F, 5F);
            };
        }

        if (e.getHitBlock() != null && e.getHitBlock().getType() != Material.BEDROCK) {
            this.tridentTask.run();
        }

    }

    @EventHandler
    public void moderatorShooter(@NotNull ProjectileLaunchEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player shooter))
            return;

        if (getUser(shooter).godMode()) {
            Vector projectile = e.getEntity().getVelocity(); //현재속도 get
            e.getEntity().setVelocity(projectile.multiply(3)); //속도 set
        }
    }

    private User getUser(Player player) {
        return new UserController().readUser(player);
    }
}
