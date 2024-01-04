package teamzesa.event;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;


public class Death extends ComponentExchanger implements Listener {
    private final UserController userController = new UserController();
    private PlayerDeathEvent event;

    @EventHandler
    public synchronized void onPlayerDeath(PlayerDeathEvent e) {
        this.event = e;
        if (checkingGodMod())
            return;

        lifeSteel();
        increaseKillingCnt();
    }

    private void increaseKillingCnt() {
        Optional.ofNullable(this.event.getEntity().getKiller()).ifPresent(
                playerType -> {
                    User user = this.userController.readUser(playerType);
                    user.increaseKillingCnt();
                    this.userController.updateUser(user);
                }
        );
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

        User killedUser = this.userController.readUser(killed);
        killedUser.setHealthScale(killed.getHealthScale() - STEP_SIZE);
        this.userController.updateUser(killedUser);
        playerSendMsgComponentExchanger(killed,killer.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);

        User killerUser = this.userController.readUser(killed);
        killerUser.setHealthScale(killer.getHealthScale() - STEP_SIZE);
        this.userController.updateUser(killerUser);
        playerSendMsgComponentExchanger(killer,killed.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
    }

    private @NotNull Boolean checkingGodMod() {
        User user = this.userController.readUser(this.event.getPlayer());

        if (!user.isGodMode())
            return false;

        Location playerLocation = this.event.getPlayer().getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        Runnable undyingEventTask = () -> {
                playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
                playerLocation.getWorld().spawnParticle(Particle.TOTEM, playerLocation, 200);
//                playerLocation.createExplosion(60);
        };

        undyingEventTask.run();
//        ThreadPool.getThreadPool().addTask(task);
//        ThreadPool.getThreadPool().executorServiceOff();
        this.event.setCancelled(true);
        return true;
    }
}
