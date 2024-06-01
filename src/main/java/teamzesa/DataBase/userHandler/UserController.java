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

    public User readUser(@NotNull Player player) {
        User user = readUser(player.getUniqueId());

        if (Optional.ofNullable(user).isEmpty())
            return null;

        if (player.getName().equals(user.name())) // 기존 유저의 이름과 변함이 없다면
            return user;

        return new UserBuilder(user) //닉네임을 변경했다면
                .name(player.getName())
                .buildAndUpdate();
    }

    public User readUser(UUID uuid) {
        return this.userDataBase.select(uuid);
    }

    public User readUser(String userName) {
        return this.userDataBase.getAllUserTable().values().stream()
                .filter(data -> data.name().equals(userName))
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
