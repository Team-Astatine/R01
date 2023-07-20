package teamzesa.userValue;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.ComponentExchanger;


public class Death implements Listener {
    private final double MAX_HEALTH_SCALE = 60.0;
    private final Double MIN_HEALTH_SCALE = 4.0;
    private final Double STEP_SIZE = 2.0;
    private final UserHandler userHandler;

    public Death() {
        userHandler = UserHandler.getUserHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        checkingGodMod(event);
        lifeSteel(event);
    }

    private void lifeSteel(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Player killer = killed.getKiller();

        if (killer == null)
            return;

        if (killed.getHealthScale() <= MIN_HEALTH_SCALE)
            return;

        if (killer.getHealthScale() >= MAX_HEALTH_SCALE)
            return;

        if (killed == killer) {
            return;
        }

        talking(killed,killer);

        double killedHealth = killed.getHealthScale() - STEP_SIZE;
        double killerHealth = killer.getHealthScale() + STEP_SIZE;

        userHandler.updateUser(killed.getUniqueId(),killedHealth);
        userHandler.updateUser(killer.getUniqueId(),killerHealth);
    }

    private void talking(Player killed, Player killer) {
        ComponentExchanger.playerAnnouncer(
                killed,killer.getName() + "님이 체력을 약탈했습니다.", "RED");
        ComponentExchanger.playerAnnouncer(
                killer,killed.getName() + "님이 체력을 약탈했습니다.", "RED");
    }

    private void checkingGodMod(PlayerDeathEvent e) {
        User user = userHandler.getUser(e.getPlayer());

        if (!user.isGodMode())
            return;

        Location playerLocation = e.getPlayer().getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
                playerLocation.getWorld().spawnParticle(Particle.TOTEM, playerLocation, 200);
                playerLocation.createExplosion(60);
            }
        };

        e.setCancelled(true);
        task.run();
    }
}
