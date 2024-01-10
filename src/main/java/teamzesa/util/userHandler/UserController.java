package teamzesa.util.userHandler;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {
    private final UserDataBase userData = UserDataBase.getUserMapHandler();

    public boolean createUser(@NotNull Player player) {
        return createUser(new UserBuilder(player)
                .build());
    }

    public boolean createUser(@NotNull User user) {
        Bukkit.getLogger().info(user.name() + "님이 신규유저 등록됐습니다.");
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
                .filter(data -> data.name().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(@NotNull User user) {
        Player player = Bukkit.getPlayer(user.uuid());
        player.setHealthScale(user.healthScale());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.healthScale());
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 40, 1));

        userData.update(user);
    }

    public void updateAllUserData(User[] newUserData) {
        userData.clear();
        Arrays.asList(newUserData).forEach(this::createUser);
    }

    public ConcurrentHashMap<UUID,User> getAllUserTable() {
        Bukkit.getLogger().info("Saving User Data..");
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        return userData.getAllUserTable();
    }

}
