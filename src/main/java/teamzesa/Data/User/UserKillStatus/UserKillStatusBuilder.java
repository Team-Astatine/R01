package teamzesa.Data.User.UserKillStatus;

import org.bukkit.entity.Player;

import java.util.UUID;

public class UserKillStatusBuilder {
    private UUID uuid;
    private double healthScale;
    private int killCount;

    public UserKillStatusBuilder() {
    }

    public UserKillStatusBuilder(UserKillStatus userKillStatus) {
        this.uuid = userKillStatus.uuid();
        healthScale(userKillStatus.healthScale());
        killCount(userKillStatus.killCount());
    }

    public UserKillStatusBuilder(Player player) {
        this.uuid = player.getUniqueId();
        healthScale(player.getHealthScale());
        killCount(0);
    }

    public UserKillStatusBuilder healthScale(double healthScale) {
        this.healthScale = healthScale;
        return this;
    }

    public UserKillStatusBuilder killCount(int killCount) {
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
        new UserKillStatusController().updateKillStatus(userKillStatus);
        return userKillStatus;
    }
}
