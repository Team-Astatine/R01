package teamzesa.user;

import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String koreanName;
    private int level;
    private double healthScale;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.level = player.getLevel();
        this.healthScale = player.getHealthScale();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getHealthScale() {
        return healthScale;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public void setKoreanName(String koreanName) {
        this.koreanName = koreanName;
    }
}
