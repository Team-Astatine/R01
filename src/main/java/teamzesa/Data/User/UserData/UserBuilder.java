package teamzesa.Data.User.UserData;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class UserBuilder {
    private UUID uuid;
    private List<String> nameList = new ArrayList<>();
    private HashSet<String> connectionIPList = new HashSet<>();
    private int joinCount;
    private int playTime;
    private int level;
    private boolean godMode;
    private boolean announcingSkip;

    public UserBuilder() {
    }

    public UserBuilder(@NotNull User user) {
        uuid(user.uuid());
        nameList(user.nameList());
        ipList(user.connectionIPList());
        joinCount(user.joinCount());
        playTime(user.playTime());
        level(user.level());
        isGodMode(user.godMode());
        isAnnouncingSkip(user.announcingSkip());
    }

    //    First Time add User
    public UserBuilder(Player player) {
        uuid(player.getUniqueId());
        nameList(player.getName());
        ipList(player.getAddress().getHostName());
        joinCount(0);
        playTime(player.getStatistic(Statistic.PLAY_ONE_MINUTE));
        level(player.getLevel());
        isGodMode(false);
        isAnnouncingSkip(true);
    }

    public UserBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public void nameList(List<String> name) {
        if (this.nameList.isEmpty())
            this.nameList = name;
        else this.nameList.retainAll(name);
    }

    public UserBuilder nameList(String name) {
        this.nameList.add(name);
        return this;
    }

    public void ipList(HashSet<String> ipList) {
        this.connectionIPList = ipList;
    }

    public UserBuilder ipList(String ip) {
        this.connectionIPList.add(ip);
        return this;
    }

    public UserBuilder joinCount(int joinCnt) {
        this.joinCount = joinCnt;
        return this;
    }

    public UserBuilder playTime(int playTime) {
        this.playTime = playTime;
        return this;
    }

    public UserBuilder level(int level) {
        this.level = level;
        return this;
    }

    public UserBuilder isGodMode(boolean currentGodMode) {
        this.godMode = currentGodMode;
        return this;
    }

    public UserBuilder isAnnouncingSkip(boolean currentAnnouncingStatus) {
        this.announcingSkip = currentAnnouncingStatus;
        return this;
    }

    public User build() {
        return new User(
                uuid,
                nameList,
                connectionIPList,
                joinCount,
                playTime,
                level,
                godMode,
                announcingSkip
        );
    }

    public User buildAndUpdate() {
        User user = build();
        new UserController().updateUser(user);
        return user;
    }
}
