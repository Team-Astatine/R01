package teamzesa.event.EntityDeathEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusBuilder;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.Enhance.BossType;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;

public class BossDeathListener extends StringComponentExchanger implements EventRegister {
    private final KillStatusController killStatusController = new KillStatusController();

    private BossType bossType;

    private Player bossSlayerPlayer;
    private UserKillStatus bossSlayerUser;

    private final EntityDeathEvent event;

    public BossDeathListener(EntityDeathEvent event) {
        this.event = event;

        this.bossType = BossType.findByEntityType(this.event.getEntityType());
        if (bossType == null)
            return;

        init();
        execute();
    }

    @Override
    public void init() {
        this.bossSlayerPlayer = this.event.getEntity().getKiller();
        this.bossSlayerUser = this.killStatusController.readUser(this.bossSlayerPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        double MAX_HEALTH_SCALE = 60.0;
        double newHealthScale = this.bossSlayerPlayer.getHealthScale() + this.bossType.getRewardHealth();

        if (bossSlayerPlayer.getHealthScale() >= 60)
            return;

        if (newHealthScale >= MAX_HEALTH_SCALE)
            newHealthScale = MAX_HEALTH_SCALE;

        this.killStatusController.healthUpdate(
                new KillStatusBuilder(this.bossSlayerUser)
                        .healthScale(newHealthScale)
                        .build()
        );

        String playerName = this.bossSlayerPlayer.getName();
        String bossName = this.bossType.getBossName();

        String comment;
        if (newHealthScale < 30)
            comment = String.format("%s님이 %s를 처치하여 체력이 %.0f 증가했습니다.",
                    playerName, bossName, this.bossSlayerPlayer.getHealthScale());

        else
            comment = String.format("%s님이 %s를 처치하여 풀 체력이 되었습니다.",
                    playerName, bossName);


        playerSendMsgComponentExchanger(this.bossSlayerPlayer, comment, ColorList.PINK);
    }
}
