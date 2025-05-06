package teamzesa.Event.LifeSteel;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import teamzesa.Data.User.UserKillStatus.UserKillStatusBuilder;
import teamzesa.Data.User.UserKillStatus.UserKillStatusController;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Enumeration.Type.BossType;
import teamzesa.Event.EventRegister;
import teamzesa.Util.Function.StringComponentExchanger;

public class BossDeathRewardHandler extends StringComponentExchanger implements EventRegister {
    private final UserKillStatusController userKillStatusController = new UserKillStatusController();

    private BossType bossType;

    private Player bossSlayerPlayer;
    private UserKillStatus bossSlayerUser;

    private final EntityDeathEvent event;

    public BossDeathRewardHandler(EntityDeathEvent event) {
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
        this.bossSlayerUser = this.userKillStatusController.readUser(this.bossSlayerPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        double MAX_HEALTH_SCALE = 60.0;
        double newHealthScale = this.bossSlayerPlayer.getHealthScale() + this.bossType.getRewardHealth();

        if (bossSlayerPlayer.getHealthScale() >= 60)
            return;

        if (newHealthScale >= MAX_HEALTH_SCALE)
            newHealthScale = MAX_HEALTH_SCALE;

        this.userKillStatusController.healthUpdate(
                new UserKillStatusBuilder(this.bossSlayerUser)
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


        playerSendMsgComponentExchanger(this.bossSlayerPlayer, comment, ColorType.PINK);
    }
}
