package teamzesa.util.userHandler;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.ComponentExchanger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserMapHandler extends ComponentExchanger {
    private static class UserMapHandlerHolder {
        private static final UserMapHandler INSTANCE = new UserMapHandler();
    }

    private static ConcurrentHashMap<UUID, User> userData;

    private UserMapHandler() {
        userData = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<UUID,User> getUserMap() {
        saveAllUserData();
        return userData;
    }

    public static UserMapHandler getUserMapHandler() {
        return UserMapHandlerHolder.INSTANCE;
    }

    public void addUser(@NotNull Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 신규유저 등록됐습니다.");
        userData.put(player.getUniqueId(), new User(player));
    }

    public User getUser(@NotNull Player player) {
        return userData.get(player.getUniqueId());
    }

    public User getUser(String userName) {
        return userData.values().stream()
                .filter(data -> data.getName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(User user) {
        userData.replace(user.getUuid(), user);
    }

    public void updateUser(UUID uuid,double healthScale) {
        Optional.ofNullable(userData.get(uuid)).ifPresentOrElse(
                user -> {
                    Player player = Bukkit.getPlayer(user.getUuid());
                    player.setHealthScale(healthScale);
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(healthScale);
                    user.setHealthScale(healthScale);
                },
                () -> Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.")
        );
    }

    public void updateAllUserData(User[] newUserData) {
        userData.clear();

        List<User> userArrayList = Arrays.asList(newUserData);
        userArrayList.forEach(user -> userData.put(user.getUuid(), user));
    }

    public void saveAllUserData() {
        Bukkit.getLogger().info("Saving User Data..");
        Bukkit.getOnlinePlayers()
                .forEach(player -> updateUser(player.getUniqueId(),player.getHealthScale()));
    }
}
