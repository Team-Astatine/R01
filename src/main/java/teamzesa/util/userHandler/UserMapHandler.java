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

    public void checkUpUser() {
        ArrayList<UUID> user = new ArrayList<>(userData.keySet());
        user.forEach(uuid -> Bukkit.getLogger().info(uuid.toString()));
    }

    public void addUser(@NotNull Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 신규유저 등록됐습니다.");
        userData.put(player.getUniqueId(), new User(player));
    }

    public void addUser(@NotNull User user) {
        Bukkit.getLogger().info(user.getName() + "님이 신규유저 등록됐습니다.");
        userData.put(user.getUuid(),user);
    }

    public User getUser(@NotNull Player player) {
        return userData.get(player.getUniqueId());
    }

    public User getUser(UUID uuid) {
        return  userData.get(uuid);
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
        User user = userData.get(uuid);
        Player player = Bukkit.getPlayer(uuid);

        if (Optional.ofNullable(user).isEmpty()) {
            Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.");
            addUser(player);
            return;
        }

        player.setHealthScale(healthScale);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(healthScale);

        user.setHealthScale(healthScale);
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
