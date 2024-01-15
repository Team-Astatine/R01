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
        return this.userData.insert(user);
    }

    public User readUser(@NotNull Player player) {
        User user = readUser(player.getUniqueId());
        if (!(player.getName().equals(user.name()))) {
            user = new UserBuilder(player)
                    .name(player.getName())
                    .build();
        }
        return user;
    }

    public User readUser(UUID uuid) {
        return this.userData.select(uuid);
    }

    public User readUser(String userName) {
        return this.userData.getAllUserTable().values().stream()
                .filter(data -> data.name().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void healthUpdate(@NotNull User user) {
        Player player = Bukkit.getPlayer(user.uuid());
        player.setHealthScale(user.healthScale());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.healthScale());
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 40, 1));
        updateUser(user);
    }

    public void updateUser(@NotNull User user) {
        this.userData.update(user);
    }

    public void updateAllUserData(User[] newUserData) {
        this.userData.clear();
        Arrays.asList(newUserData).forEach(this::createUser);
    }

    public ConcurrentHashMap<UUID,User> getAllUserTable() {
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        return this.userData.getAllUserTable();
    }

}
