package teamzesa.event;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.dataValue.User;
import teamzesa.util.userHandler.UserMapHandler;



public class Death extends ComponentExchanger implements Listener {
    private final UserMapHandler userMapHandler;
    private PlayerDeathEvent event;

    public Death() {
        this.userMapHandler = UserMapHandler.getUserMapHandler();
    }

    @EventHandler
    public synchronized void onPlayerDeath(PlayerDeathEvent e) {
        this.event = e;
        if (checkingGodMod())
            return;

        lifeSteel();
    }

    private void lifeSteel() {
        double MAX_HEALTH_SCALE = 60.0;
        double MIN_HEALTH_SCALE = 4.0;
        double STEP_SIZE = 2.0;

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

        this.userMapHandler.updateUser(killed.getUniqueId(),killedHealth);
        this.userMapHandler.updateUser(killer.getUniqueId(),killerHealth);
    }

    private void talking(Player killed, @NotNull Player killer) {
        playerSendMsgComponentExchanger(killed,killer.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
        playerSendMsgComponentExchanger(killer,killed.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
    }

    private @NotNull Boolean checkingGodMod() {
        User user = this.userMapHandler.getUser(this.event.getPlayer());

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
//        ThreadPool.getThreadPool().addTask(task);
        this.event.setCancelled(true);
        return true;
    }
}
