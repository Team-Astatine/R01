package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.userData.UserHandler;

import java.awt.*;

public class HealthSet extends ComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PotionEffect healEffect = new PotionEffect(PotionEffectType.HEAL, 40, 1);
        UserHandler userHandler = UserHandler.getUserHandler();
        Player player = Bukkit.getPlayer(args[0]);

        if (player == null)
            return false;

        player.addPotionEffect(healEffect);
        player.setHealthScale(Double.parseDouble(args[1]));
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Double.parseDouble(args[1]));
        userHandler.updateUser(player, player.getHealthScale());

        playerAnnouncer(player,args[0] + "님의 체력이" + args[1] + "으로 설정됐습니다.", Color.YELLOW);
        return true;
    }
}
