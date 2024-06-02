package teamzesa.DataBase.userHandler;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.User;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {
    private final DaoUser userDataBase = DaoUser.getInstance();

    public boolean createUser(@NotNull Player player) {
        return createUser(
                new UserBuilder(player)
                .buildAndUpdate()
        );
    }

    public boolean createUser(@NotNull User user) {
        return this.userDataBase.insert(user);
    }

    public User readUser(UUID uuid) {
        return this.userDataBase.select(uuid);
    }

    public User readUser(String userName) {
        return this.userDataBase.getAllUserTable().values().stream()
                .filter(data -> data.nameList().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void healthUpdate(@NotNull User user) {
        Player player = Bukkit.getPlayer(user.uuid());
        player.setHealthScale(user.healthScale());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.healthScale());
        player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 40, 1));
        updateUser(user);
    }

    public void updateUser(@NotNull User user) {
        this.userDataBase.update(user);
    }

    public void updateAllUserData(User[] newUserData) {
        this.userDataBase.clear();
        Arrays.asList(newUserData).forEach(this::createUser);
    }

    public ConcurrentHashMap<UUID,User> getAllUserTable() {
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        return this.userDataBase.getAllUserTable();
    }

}
