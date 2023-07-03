package teamzesa.userValue;

import net.kyori.adventure.text.format.TextColor;
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

    public User getUser(UUID uuid) {
        return  userData.get(uuid);
    }

    public User getUser(Player player) {
        return  userData.get(player.getUniqueId());
    }

    public void updateUser(User user) {
        userData.replace(user.getUuid(), user);
    }

    public void updateUser(UUID uuid,double healthScale) {
        User user = userData.get(uuid);
        Player player = Bukkit.getPlayer(uuid);

        if (user == null) {
            Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.");
            addUser(player);
        }

        user.setHealthScale(healthScale);
        player.setHealthScale(healthScale);
        updateUser(user);
    }

    public void updateAllUserData(User[] newUserData) {
        for (User user : newUserData) {
            userData.put(user.getUuid(), user);
//            Bukkit.getLogger().info(user.getName());
        }
    }

    public void saveAllUserData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateUser(player.getUniqueId(), player.getHealthScale());
        }
    }
}
