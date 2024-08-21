package teamzesa.DataBase.UserKillStatusHandler;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.RObject.UserKillStatus;

import java.util.ArrayList;
import java.util.UUID;

public class KillStatusController {
    private final DaoUserKillStatus userKillStatus = DaoUserKillStatus.getInstance();

    public boolean createUserKillStatus(Player player) {
        return createUserKillStatus(
                new KillStatusBuilder(player)
                        .buildAndUpdate()
        );
    }

    public boolean createUserKillStatus(@NotNull UserKillStatus userKillStatus) {
        return this.userKillStatus.insert(userKillStatus);
    }

    public UserKillStatus readUser(UUID uuid) {
        return this.userKillStatus.select(uuid);
    }

    public void updateKillStatus(@NotNull UserKillStatus userKillStatus) {
        this.userKillStatus.update(userKillStatus);
    }

    public void healthUpdate(@NotNull UserKillStatus userKillStatus) {
        Player player = Bukkit.getPlayer(userKillStatus.uuid());
        player.setHealthScale(userKillStatus.healthScale());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(userKillStatus.healthScale());
        player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 40, 1));
        updateKillStatus(userKillStatus);
    }

    public void updateAllUserData(ArrayList<UserKillStatus> newUserData) {
        this.userKillStatus.clear();

        if (ObjectUtils.allNull(newUserData))
            return;

        newUserData.forEach(this::createUserKillStatus);
    }

    public ArrayList<UserKillStatus> getAllUserTable() {
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        return new ArrayList<>(this.userKillStatus.getAllUserTable().values());
    }
}
