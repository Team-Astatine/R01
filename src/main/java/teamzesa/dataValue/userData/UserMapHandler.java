package teamzesa.dataValue.userData;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;

import java.util.*;

public class UserMapHandler extends ComponentExchanger {
    private static class UserMapHandlerHolder {
        private static final UserMapHandler INSTANCE = new UserMapHandler();
    }

    private static Map<UUID, User> userData;

    private UserMapHandler() {
        userData = new HashMap<>();
    }

    public Map<UUID,User> getUserMap() {
        saveAllUserData();
        return userData;
    }

    public static UserMapHandler getUserHandler() {
        return UserMapHandlerHolder.INSTANCE;
    }

    public void checkUpUser() {
        List<UUID> user = new ArrayList<>(userData.keySet());
        user.forEach(uuid -> Bukkit.getLogger().info(uuid.toString()));
    }

    public synchronized void addUser(Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 신규유저 등록됐습니다.");
        userData.put(player.getUniqueId(), new User(player));
    }

    public User getUser(Player player) {
        return userData.get(player.getUniqueId());
    }

    public User getUser(CommandSender sender) {
        return userData.get((Player)sender);
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

    public Player getPlayer(String userName) {
        return Bukkit.getPlayer(userName);
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
            return;
        }

        user.setHealthScale(healthScale);
        player.setHealthScale(healthScale);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(healthScale);
        updateUser(user);
    }

    public void updateUser(Player player,double healthScale) {
        User user = userData.get(player.getUniqueId());
        user.setHealthScale(healthScale);
    }

    public void updateAllUserData(User[] newUserData) {
        userData.clear();
        Arrays.stream(newUserData)
                .forEach(user -> userData.put(user.getUuid(), user));
    }

    public void saveAllUserData() {
        Bukkit.getLogger().info("Saving User Data..");
        Bukkit.getOnlinePlayers().stream()
                .forEach(player -> updateUser(player.getUniqueId(),player.getHealthScale()));
    }
}
