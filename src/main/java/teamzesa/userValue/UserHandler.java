package teamzesa.userValue;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserHandler implements Listener {
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

    public void updateUser(UUID uuid,String koreanName) {
        User user = userData.get(uuid);

        if (user == null) {
            Bukkit.getLogger().warning("해당 유저는 존재하지 않습니다.");
            return;
        }

        user.setKoreanName(koreanName);
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

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (getUser(player.getUniqueId()) == null)
            addUser(player);

        User user = getUser(player.getUniqueId());

        player.setHealthScale(user.getHealthScale());
        player.setPlayerListName(user.getKoreanName());
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (player.getHealthScale() == 20.0)
            return;

        updateUser(player.getUniqueId(), player.getHealthScale());
    }
}
