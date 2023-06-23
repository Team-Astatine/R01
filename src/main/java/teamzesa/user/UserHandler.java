package teamzesa.user;

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

    public static UserHandler getInstance() {
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

    public User getUser(Player player) {
        return  userData.get(player.getUniqueId());
    }

    public void updateUser(User user) {
        userData.replace(user.getUuid(), user);
    }

    public void removeUser(Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 삭제됐습니다.");
        userData.remove(player.getUniqueId());
    }
}
