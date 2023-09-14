package teamzesa.userEvent;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.ThreadPool;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;


public class Death extends ComponentExchanger implements Listener {
    private final double MAX_HEALTH_SCALE = 60.0;
    private final Double MIN_HEALTH_SCALE = 4.0;
    private final Double STEP_SIZE = 2.0;
    private final UserMapHandler userMapHandler;
    private final ThreadPool threadPool;

    public Death() {
        threadPool = ThreadPool.getThreadPool();
        userMapHandler = UserMapHandler.getUserHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (checkingGodMod(event))
            return;
        lifeSteel(event);
    }

    private void lifeSteel(@NotNull PlayerDeathEvent e) {
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

        userMapHandler.updateUser(killed.getUniqueId(),killedHealth);
        userMapHandler.updateUser(killer.getUniqueId(),killerHealth);
    }

    private void talking(Player killed, @NotNull Player killer) {
        playerAnnouncer(killed,killer.getName() + "님이 체력을 약탈했습니다.", Color.RED);
        playerAnnouncer(killer,killed.getName() + "님이 체력을 약탈했습니다.", Color.RED);
    }

    private @NotNull Boolean checkingGodMod(@NotNull PlayerDeathEvent e) {
        User user = userMapHandler.getUser(e.getPlayer());

        if (!user.isGodMode())
            return false;

        Location playerLocation = e.getPlayer().getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
                playerLocation.getWorld().spawnParticle(Particle.TOTEM, playerLocation, 200);
//                playerLocation.createExplosion(60);
            }
        };
        threadPool.addTask(task);
        e.setCancelled(true);
        return true;
    }
}
