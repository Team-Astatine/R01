package teamzesa.event.PlayerDeathEvent;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusBuilder;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Interface.StringComponentExchanger;


public class LifeSteelEvent extends StringComponentExchanger implements EventRegister {
    private final UserController userController = new UserController();
    private final KillStatusController userKillStatus = new KillStatusController();

    private Player deather;
    private Player killer;

    private User deatherUser;
    private UserKillStatus deatherUserKillStatus;
    private UserKillStatus killerUserKillStatus;

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
        this.deatherUser = this.userController.readUser(this.deather.getUniqueId());
        this.deatherUserKillStatus = this.userKillStatus.readUser(this.deather.getUniqueId());
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
        if (BooleanUtils.isFalse(this.deatherUser.isGodMode()))
            return false;

        Location playerLocation = this.deather.getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        Runnable undyingEventTask = () -> {
            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
            playerLocation.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, playerLocation, 200);
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
            this.event.deathMessage(componentExchanger(this.deatherUser.nameList().getLast() + " 님이 자살했습니다.", ColorMap.RED));
            return true;
        }

        return false;
    }

    private void lifeSteel() {
        this.killerUserKillStatus = this.userKillStatus.readUser(this.killer.getUniqueId());

        double MAX_HEALTH_SCALE = 60.0;
        double MIN_HEALTH_SCALE = 4.0;
        double STEP_SIZE = 2.0;

        //valid Logic
        if (this.deather.getHealthScale() <= MIN_HEALTH_SCALE ||
                this.killer.getHealthScale() >= MAX_HEALTH_SCALE ||
                this.deather == killer)
            return;

        //execute Logic
        this.userKillStatus.healthUpdate(
                new KillStatusBuilder(this.deatherUserKillStatus)
                        .healthScale(this.deather.getHealthScale() - STEP_SIZE)
                        .build());

        this.userKillStatus.healthUpdate(
                new KillStatusBuilder(this.killerUserKillStatus)
                        .healthScale(this.killer.getHealthScale() + STEP_SIZE)
                        .killCount(this.killerUserKillStatus.killCount() + 1)
                        .build());

        playerSendMsgComponentExchanger(this.deather, this.killer.getName() + "님이 체력을 약탈했습니다.", ColorMap.RED);
        playerSendMsgComponentExchanger(this.killer, this.deather.getName() + "님이 체력을 약탈했습니다.", ColorMap.RED);

        String deathUserName = this.userController.readUser(this.killer.getUniqueId()).nameList().getLast();
        this.event.deathMessage(
                componentExchanger("[KILL]" + deathUserName + " -> " + this.deatherUser.nameList().getLast(), ColorMap.PINK)
        );
    }
}
