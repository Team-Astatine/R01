package teamzesa.util.userHandler;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;

import java.util.HashSet;
import java.util.UUID;

public class UserBuilder {
    private UUID uuid;
    private String name;
    private HashSet<String> connectionIPList = new HashSet<>();
    private int joinCount;
    private int level;
    private double healthScale;
    private int killStatus;
    private boolean isGodMode;
    private boolean isAnnouncing;

    public UserBuilder(){}

    public UserBuilder(@NotNull User user) {
        this.uuid = user.uuid();
        this.name = user.name();
        this.connectionIPList = user.connectionIPList();
        this.joinCount = user.joinCount();
        this.level = user.level();
        this.healthScale = user.healthScale();
        this.killStatus = user.killStatus();
        this.isGodMode = user.isGodMode();
        this.isAnnouncing = user.isAnnouncing();
    }

//    First Time add User
    public UserBuilder(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.connectionIPList.add(player.getAddress().getAddress().getHostAddress());
        this.joinCount = 1;
        this.level = player.getLevel();
        this.killStatus = 0;
        this.healthScale = player.getHealthScale();
        this.isGodMode = false;
        this.isAnnouncing = false;
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

    public UserBuilder joinCount(int joinCnt) {
        this.joinCount = joinCnt;
        return this;
    }

    public UserBuilder level(int level) {
        return this;
    }

    public UserBuilder healthScale(double healthScale) {
        this.healthScale = healthScale;
        return this;
    }

    public UserBuilder killStatus(int killStatus) {
        this.killStatus = killStatus;
        return this;
    }

    public UserBuilder godMode(boolean currentGodMode) {
        this.isGodMode = currentGodMode;
        return this;
    }

    public UserBuilder announcing(boolean currentAnnouncing) {
        this.isAnnouncing = currentAnnouncing;
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
                killStatus,
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
