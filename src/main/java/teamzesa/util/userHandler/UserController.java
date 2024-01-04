package teamzesa.util.userHandler;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {
    private final UserDataBase userData = UserDataBase.getUserMapHandler();

    public boolean createUser(@NotNull Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 신규유저 등록됐습니다.");
        return userData.insert(player);
    }

    public boolean createUser(@NotNull User user) {
        Bukkit.getLogger().info(user.getName() + "님이 신규유저 등록됐습니다.");
        return userData.insert(user);
    }

    public User readUser(@NotNull Player player) {
        return readUser(player.getUniqueId());
    }

    public User readUser(UUID uuid) {
        return  userData.select(uuid);
    }

    public User readUser(String userName) {
        return userData.getAllUserTable().values().stream()
                .filter(data -> data.getName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(@NotNull User user) {
        Player player = Bukkit.getPlayer(user.getUniqueId());
        player.setHealthScale(user.getHealthScale());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.getHealthScale());

        userData.update(user);
    }

    public void updateAllUserData(User[] newUserData) {
        userData.clear();
        List<User> userArrayList = Arrays.asList(newUserData);
        userArrayList.forEach(this::createUser);
    }

    public ConcurrentHashMap<UUID,User> getAllUserTable() {
        Bukkit.getLogger().info("Saving User Data..");
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        return userData.getAllUserTable();
    }
}
