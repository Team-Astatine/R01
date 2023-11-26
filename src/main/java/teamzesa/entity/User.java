package teamzesa.entity;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

//public record User (
//        UUID uuid,
//        String name,
//        Set<String> ip,
//        int joinCount,
//        int level,
//        double healthScale,
//        boolean godMode
//        ) {}

    public class User {
    private UUID uuid;
    private String name;
    private Set<String> ip = new HashSet<>();
    private int joinCount;
    private int level;
    private double healthScale;
    private boolean godMode;

    public User(@NotNull Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.ip.add(player.getAddress().getAddress().getHostAddress());
        this.joinCount = 0; //join Event 에서 1회 ++ 증가시킴
        this.level = player.getLevel();
        this.healthScale = player.getHealthScale();
        this.godMode = false;
    }

//    getter
    public UUID getUuid() {
        return uuid;
    }

    public double getHealthScale() {
        return healthScale;
    }

    public String getName() {
        return name;
    }

    public Set<String> getIPList() {
        return ip;
    }

    public int getJoinCount() {
        return joinCount;
    }

    public boolean isGodMode() {
        return godMode;
    }

//    setter userHandler
    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public void setIp(Set<String> ip) {
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
