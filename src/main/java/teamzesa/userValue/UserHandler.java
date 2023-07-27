package teamzesa.userValue;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;

import java.util.*;

public class UserHandler extends ComponentExchanger {
    private static class UserHandlerHolder {
        private static UserHandler INSTANCE = new UserHandler();
    }

    private static Map<UUID, User> userData;

    private UserHandler() {
        userData = new HashMap<>();
    }

    public Map<UUID,User> getUserMap() {
        saveAllUserData();
        return userData;
    }

    public static UserHandler getUserHandler() {
        return UserHandlerHolder.INSTANCE;
    }

    public void checkUpUser() {
        List<UUID> user = new ArrayList<>(userData.keySet());
        user.forEach(uuid -> Bukkit.getLogger().info(uuid.toString()));
    }

    public void addUser(Player player) {
        Bukkit.getLogger().info(player.getName() + "님이 신규유저 등록됐습니다.");
        userData.put(player.getUniqueId(), new User(player));
    }

    public User getUser(Player player) {
        return  userData.get(player.getUniqueId());
    }

    public User getUser(CommandSender sender) {
        return userData.get((Player)sender);
    }

    public User getUser(UUID uuid) {
        return  userData.get(uuid);
    }

    public User getUser(String userName) {
        return getUser(Bukkit.getPlayer(userName));
    }

    public Player getPlayer(String playerName) {
        return Bukkit.getPlayer(getUser(playerName).getUuid());
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
        updateUser(user);
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
