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
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;


public class Death extends ComponentExchanger implements Listener {
    private final UserController userController = new UserController();
    private Player deather;
    private Player killer;
    private User deatherUser;
    private User killerUser;

    @EventHandler
    public synchronized void onPlayerDeath(PlayerDeathEvent e) {
        this.deather = e.getPlayer();
        this.killer = deather.getKiller();

        //무조건 플레이어가 죽여야함
        if (this.killer == null)
            return;

        //스스로가 스스로를 죽이면 무시함
        if (this.deather.equals(this.killer)) {
            playerSendMsgComponentExchanger(this.deather, "자살하셨습니다!", ColorList.RED);
            return;
        }

        this.deatherUser = this.userController.readUser(this.deather);
        this.killerUser = this.userController.readUser(this.killer);

        if (checkingGodMod())
            return;

        lifeSteel();
    }

    private void init() {
    }

    private void lifeSteel() {
        double MAX_HEALTH_SCALE = 60.0;
        double MIN_HEALTH_SCALE = 4.0;
        double STEP_SIZE = 2.0;

        //valid Logic
        if (this.deather.getHealthScale() <= MIN_HEALTH_SCALE ||
            this.killer.getHealthScale()  >= MAX_HEALTH_SCALE ||
            this.deather == killer)
            return;

        updateUserHealthScaleData(STEP_SIZE);
    }

    private void updateUserHealthScaleData(double STEP_SIZE) {
        new UserController().healthUpdate(
                new UserBuilder(this.deatherUser)
                .healthScale(this.deather.getHealthScale() - STEP_SIZE)
                .build());

        new UserController().healthUpdate(
                new UserBuilder(this.killerUser)
                .healthScale(this.killer.getHealthScale() + STEP_SIZE)
                .killStatus(this.killerUser.killStatus() + 1)
                .build());

        playerSendMsgComponentExchanger(this.deather,killer.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
        playerSendMsgComponentExchanger(this.killer,this.deather.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
    }

    private @NotNull Boolean checkingGodMod() {
        if (!this.deatherUser.godMode())
            return false;

        Location playerLocation = this.deather.getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        Runnable undyingEventTask = () -> {
                playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
                playerLocation.getWorld().spawnParticle(Particle.TOTEM, playerLocation, 200);
//                playerLocation.createExplosion(60);
        };

        undyingEventTask.run();
        return true;
    }
}
