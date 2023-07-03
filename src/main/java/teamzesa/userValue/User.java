package teamzesa.userValue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String prefix;
    private TextColor color;
    private int level;
    private double healthScale;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.prefix = null;
        this.color = null;
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

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setColor(TextColor color) {
        this.color = color;
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
