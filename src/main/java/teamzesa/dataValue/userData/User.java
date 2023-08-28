package teamzesa.dataValue.userData;

import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.*;

public class User {
    private UUID uuid;
    private String name;
    private List<String> ip = new ArrayList<>();;
    private int joinCount;
    private int level;
    private double healthScale;
    private boolean godMode;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.ip.add(player.getAddress().getAddress().getHostAddress());
        this.joinCount = 1; //최초 등록 자체가 1회 접속임.
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

    public List<String> getIPList() {
        return ip;
    }

    public int getJoinCount() {
        return joinCount;
    }

    public boolean isGodMode() {
        return godMode;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    public void setJoinCount(int count) {
        this.joinCount = count;
    }

    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", ip=" + ip +
                ", joinCount=" + joinCount +
                ", level=" + level +
                ", healthScale=" + healthScale +
                ", godMode=" + godMode +
                '}';
    }
}
