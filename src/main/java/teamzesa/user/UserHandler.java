package teamzesa.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserHandler {
    private static UserHandler instance = new UserHandler();
    private static Map<UUID, User> userData;

    private UserHandler() {
        this.userData = new HashMap<>();
    }

    public static UserHandler getInstance() {
        return instance;
    }

    public void addUser(Player player) {
        userData.put(player.getUniqueId(), new User(player));
    }

    public User getUser(Player player) {
        return  userData.get(player.getUniqueId());
    }

    public void updateUser(Player player) {
        userData.replace(player.getUniqueId(), new User(player));
    }

    public void removeUser(Player player) {
        userData.remove(player.getUniqueId());
    }
}
