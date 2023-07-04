package teamzesa.userValue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private int level;
    private double healthScale;
    private String prefix;
    private Integer colorCode;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.level = player.getLevel();
        this.healthScale = player.getHealthScale();
        this.prefix = null;
        this.colorCode = null;
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

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setColorCode(Integer colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", healthScale=" + healthScale +
                ", prefix='" + prefix + '\'' +
                ", colorCode=" + colorCode +
                '}';
    }
}
