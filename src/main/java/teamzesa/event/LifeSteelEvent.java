package teamzesa.event;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;


public class LifeSteelEvent extends StringComponentExchanger implements EventRegister {
    private final UserController userController = new UserController();
    private Player deather;
    private Player killer;
    private User deatherUser;
    private User killerUser;
    private final PlayerDeathEvent event;

    public LifeSteelEvent(PlayerDeathEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.deather = this.event.getPlayer();
        this.killer = deather.getKiller();
        this.deatherUser = this.userController.readUser(this.deather);
    }

    @Override
    public void execute() {
        if (checkingGodMod())
            return;
        if (validKiller())
            return;
        lifeSteel();
    }

    private boolean checkingGodMod() {
        if (!this.deatherUser.isGodMode())
            return false;

        Location playerLocation = this.deather.getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        Runnable undyingEventTask = () -> {
            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
            playerLocation.getWorld().spawnParticle(Particle.TOTEM, playerLocation, 200);
//                playerLocation.createExplosion(60);
        };

        undyingEventTask.run();
        this.event.setCancelled(true);
        return true;
    }

    private boolean validKiller() {
        //무조건 플레이어가 죽여야함
        if (this.killer == null)
            return true;

        //스스로가 스스로를 죽이면 무시함
        if (this.deather.equals(this.killer)) {
            this.event.deathMessage(componentExchanger(this.deatherUser.name() + " 님이 자살했습니다.", ColorList.RED));
            return true;
        }

        return false;
    }

    private void lifeSteel() {
        this.killerUser = this.userController.readUser(this.killer);

        double MAX_HEALTH_SCALE = 60.0;
        double MIN_HEALTH_SCALE = 4.0;
        double STEP_SIZE = 2.0;

        //valid Logic
        if (this.deather.getHealthScale() <= MIN_HEALTH_SCALE ||
                this.killer.getHealthScale() >= MAX_HEALTH_SCALE ||
                this.deather == killer)
            return;

        updateUserHealthScaleData(STEP_SIZE);
    }

    private void updateUserHealthScaleData(double STEP_SIZE) {
        this.userController.healthUpdate(
                new UserBuilder(this.deatherUser)
                        .healthScale(this.deather.getHealthScale() - STEP_SIZE)
                        .build());

        this.userController.healthUpdate(
                new UserBuilder(this.killerUser)
                        .healthScale(this.killer.getHealthScale() + STEP_SIZE)
                        .killStatus(this.killerUser.killStatus() + 1)
                        .build());

        playerSendMsgComponentExchanger(this.deather, this.killer.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
        playerSendMsgComponentExchanger(this.killer, this.deather.getName() + "님이 체력을 약탈했습니다.", ColorList.RED);
        this.event.deathMessage(
                componentExchanger("[KILL]" + this.killerUser.name() + " -> " + this.deatherUser.name(), ColorList.PINK)
        );
    }
}
