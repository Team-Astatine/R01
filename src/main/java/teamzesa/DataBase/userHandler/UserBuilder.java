package teamzesa.DataBase.userHandler;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.User;

import java.util.HashSet;
import java.util.UUID;

public class UserBuilder {
    private UUID uuid;
    private String name;
    private HashSet<String> connectionIPList = new HashSet<>();
    private int joinCount;
    private int level;
    private double healthScale;
    private int killCount;
    private boolean isGodMode;
    private boolean isAnnouncing;

    public UserBuilder(){}

    public UserBuilder(@NotNull User user) {
        uuid(user.uuid());
        name(user.name());
        ipList(user.connectionIPList());
        joinCount(user.joinCount());
        level(user.level());
        healthScale(user.healthScale());
        killCount(user.killCount());
        isGodMode(user.isGodMode());
        isAnnouncing(user.isAnnouncing());
    }

//    First Time add User
    public UserBuilder(Player player) {
        uuid(player.getUniqueId());
        name(player.getName());
        ipList(player.getAddress().getHostName());
        joinCount(0);
        level(player.getLevel());
        healthScale(player.getHealthScale());
        killCount(0);
        isGodMode(false);
        isAnnouncing(false);
    }

    public UserBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder ipList(HashSet<String> ipList) {
        this.connectionIPList = ipList;
        return this;
    }

    public UserBuilder ipList(String ip) {
        this.connectionIPList.add(ip);
        return this;
    }

    public UserBuilder joinCount(int joinCnt) {
        this.joinCount = joinCnt;
        return this;
    }

    public UserBuilder level(int level) {
        this.level = level;
        return this;
    }

    public UserBuilder healthScale(double healthScale) {
        this.healthScale = healthScale;
        return this;
    }

    public UserBuilder killCount(int currentKillCount) {
        this.killCount = currentKillCount;
        return this;
    }

    public UserBuilder isGodMode(boolean currentGodMode) {
        this.isGodMode = currentGodMode;
        return this;
    }

    public UserBuilder isAnnouncing(boolean currentAnnouncingStatus) {
        this.isAnnouncing = currentAnnouncingStatus;
        return this;
    }

    public User build() {
        return new User(
                uuid,
                name,
                connectionIPList,
                joinCount,
                level,
                healthScale,
                killCount,
                isGodMode,
                isAnnouncing
        );
    }

    public User buildAndUpdate() {
        User user = build();
        new UserController().updateUser(user);
        return user;
    }
}
