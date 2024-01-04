package teamzesa.entity;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
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
    private Set<String> connectionIPList = new HashSet<>();
    private int joinCount;
    private int level;
    private double healthScale;
    private int killStatus;
    private boolean godMode;

    public User(@NotNull Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.connectionIPList.add(player.getAddress().getAddress().getHostAddress());
        this.joinCount = 0; //join Event 에서 1회 ++ 증가시킴
        this.level = player.getLevel();
        this.killStatus = 0;
        this.healthScale = player.getHealthScale();
        this.godMode = false;
    }

    public User(User user) {
        this.uuid = user.uuid;
        this.name = user.name;
        this.connectionIPList = user.connectionIPList;
        this.joinCount = user.joinCount;
        this.level = user.level;
        this.healthScale = user.healthScale;
        this.killStatus = user.killStatus;
        this.godMode = user.godMode;
    }

    //    getter
    public UUID getUniqueId() {
        return uuid;
    }

    public double getHealthScale() {
        return healthScale;
    }

    public String getName() {
        return name;
    }

    public int getJoinCount() {
        return joinCount;
    }

    public int getKillStatus(){
        return this.killStatus;
    }

    public boolean isGodMode() {
        return godMode;
    }


    //    setter userHandler
    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public Boolean nonExistsIP(InetSocketAddress ip) {
        return this.connectionIPList.stream()
                .noneMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public void addIP(@NotNull InetSocketAddress ip) {
        this.connectionIPList.add(ip.getAddress().getHostAddress());
    }

    public void increaseUserJoinCnt() {
        this.joinCount += 1;
    }

    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }

    public void increaseKillingCnt() {
        this.killStatus += 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", connectionIPList=" + connectionIPList +
                ", joinCount=" + joinCount +
                ", level=" + level +
                ", healthScale=" + healthScale +
                ", killStatus=" + killStatus +
                ", godMode=" + godMode +
                '}';
    }
}
