package teamzesa.userEvent;

import org.bukkit.*;
import org.bukkit.block.Block;
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
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;
import java.awt.Color;


public class Death extends ComponentExchanger implements Listener {
    private final double MAX_HEALTH_SCALE = 60.0;
    private final Double MIN_HEALTH_SCALE = 4.0;
    private final Double STEP_SIZE = 2.0;

    private final UserMapHandler userMapHandler;
    private final ThreadPool threadPool;

    private PlayerDeathEvent event;

    public Death() {
        threadPool = ThreadPool.getThreadPool();
        userMapHandler = UserMapHandler.getUserHandler();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        this.event = e;
        if (checkingGodMod())
            return;

        lifeSteel();
    }

    private void lifeSteel() {
        Player killed = this.event.getEntity();
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

    private @NotNull Boolean checkingGodMod() {
        User user = userMapHandler.getUser(this.event.getPlayer());

        if (!user.isGodMode())
            return false;

        Location playerLocation = this.event.getPlayer().getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
                playerLocation.getWorld().spawnParticle(Particle.TOTEM, playerLocation, 200);
//                playerLocation.createExplosion(60);
            }
        };
        task.run();
//        threadPool.addTask(task);
//        threadPool.executorServiceOff();
        this.event.setCancelled(true);
        return true;
    }
}
