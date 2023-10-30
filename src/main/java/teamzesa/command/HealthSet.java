package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.resgisterEvent.EventExecutor;


public class HealthSet implements CommandExecutor, EventExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String @NotNull [] args) {
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        double targetPlayerHealth = Double.parseDouble(args[1]);
        
        if (targetPlayer == null) {
            return false;
        }

        setPlayerHealth(targetPlayerHealth, targetPlayer);
        updatePlayerInfo(targetPlayer);

        return true;
    }

    private void updatePlayerInfo(@NotNull Player targetPlayer) {
        UserMapHandler userMapHandler = UserMapHandler.getUserMapHandler();
        userMapHandler.updateUser(targetPlayer.getUniqueId(), targetPlayer.getHealthScale());
    }

    private void setPlayerHealth(double targetPlayerHealth, @NotNull Player targetPlayer) {
        targetPlayer.setHealthScale(targetPlayerHealth);
        targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 40, 1));
        targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(targetPlayerHealth);
        ComponentExchanger.playerAnnouncer(targetPlayer,targetPlayer.getName() + "님의 체력이" + targetPlayerHealth + "으로 설정됐습니다.", ColorList.YELLOW);
    }
}
