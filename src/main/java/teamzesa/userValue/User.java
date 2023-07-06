package teamzesa.userValue;

import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
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

    public int getLevel() {
        return level;
    }

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", healthScale=" + healthScale +
                '}';
    }
}
