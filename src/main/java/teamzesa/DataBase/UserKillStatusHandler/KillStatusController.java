package teamzesa.DataBase.UserKillStatusHandler;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.RObjectIOHandler;
import teamzesa.DataBase.entity.RObject;
import teamzesa.DataBase.entity.User;
import teamzesa.DataBase.entity.UserKillStatus;
import teamzesa.command.SaveR01ObjectData;
import teamzesa.util.Enum.DataFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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

    public void updateAllUserData(ArrayList<RObject> newUserData) {
        this.userKillStatus.clear();

        if (newUserData == null)
            return;

        for (RObject rObject : newUserData) {
            if (rObject instanceof UserKillStatus userKillStatus)
                createUserKillStatus(userKillStatus);
        }
    }

    public ArrayList<RObject> getAllUserTable() {
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        ArrayList<RObject> totalKillStatusData = new ArrayList<>();
        for (UserKillStatus killStatus : this.userKillStatus.getAllUserTable().values()) {
            if (killStatus instanceof RObject rObject)
                totalKillStatusData.add(rObject);
        }
        return totalKillStatusData;
    }
}
