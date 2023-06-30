package teamzesa.userValue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserHandler {
    private static UserHandler instance = new UserHandler();
    private static Map<UUID, User> userData;

    private UserHandler() {
        userData = new HashMap<>();
    }

    public Map<UUID,User> getUserMap() {
        return userData;
    }

    public static UserHandler getUserHandler() {
        return instance;
    }

    public void addUser(Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 신규유저 등록됐습니다.");
        userData.put(player.getUniqueId(), new User(player));
    }

    public void updateUserData(User[] newUserData) {
        for (User user : newUserData) {
            userData.put(user.getUuid(), user);
//            Bukkit.getLogger().info(user.getName());
        }
    }

    public User getUser(UUID uuid) {
        return  userData.get(uuid);
    }

    public void updateUser(User user) {
        userData.replace(user.getUuid(), user);
    }

    public void updateDyingUser(Player player) {
        Player p = player;
        p.setLevel(0);
        p.setHealthScale(20);

        User user = userData.get(player.getUniqueId());
        user.setLevel(p.getLevel());
        user.setHealthScale(p.getHealthScale());

        userData.replace(p.getUniqueId(),user);
    }

    public void updateUser(UUID uuid,double healthScale,int level) {
        User user = userData.get(uuid);

        if (user == null) {
            Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.");
            return;
        }

        user.setHealthScale(healthScale);
        user.setLevel(level);
        updateUser(user);
    }

    public void updateUser(UUID uuid,double healthScale) {
        User user = userData.get(uuid);

        if (user == null) {
            Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.");
            return;
        }

        user.setHealthScale(healthScale);
        updateUser(user);
    }

    public void updateUser(UUID uuid,String koreanName) {
        User user = userData.get(uuid);

        if (user == null) {
            Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.");
            return;
        }

        user.setKoreanName(koreanName);
        updateUser(user);
    }

    public void removeUser(Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 삭제됐습니다.");
        userData.remove(player.getUniqueId());
    }
}
