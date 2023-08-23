package teamzesa.dataValue.userData;

import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private int level;
    private double healthScale;
    private boolean godMode;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.level = player.getLevel();
        this.healthScale = player.getHealthScale();
        this.godMode = false;
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

    public String getName() {
        return name;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }

    public boolean isGodMode() {
        return godMode;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", healthScale=" + healthScale +
                ", godMode=" + godMode +
                '}';
    }
}
