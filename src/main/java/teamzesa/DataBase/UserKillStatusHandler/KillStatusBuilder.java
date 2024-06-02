package teamzesa.DataBase.UserKillStatusHandler;

import org.bukkit.entity.Player;
import teamzesa.DataBase.entity.UserKillStatus;

import java.util.UUID;

public class KillStatusBuilder {
    private UUID uuid;
    private double healthScale;
    private int killCount;

    public KillStatusBuilder() {}

    public KillStatusBuilder(UserKillStatus userKillStatus) {
        this.uuid = userKillStatus.uuid();
        healthScale(userKillStatus.healthScale());
        killCount(userKillStatus.killCount());
    }

    public KillStatusBuilder(Player player) {
        this.uuid = player.getUniqueId();
        healthScale(player.getHealthScale());
        killCount(0);
    }

    public KillStatusBuilder healthScale(double healthScale) {
        this.healthScale = healthScale;
        return this;
    }

    public KillStatusBuilder killCount(int killCount) {
        this.killCount = killCount;
        return this;
    }

    public UserKillStatus build() {
        return new UserKillStatus(
                uuid,
                healthScale,
                killCount
        );
    }

    public UserKillStatus buildAndUpdate() {
        UserKillStatus userKillStatus = build();
        new KillStatusController().updateKillStatus(userKillStatus);
        return userKillStatus;
    }
}
