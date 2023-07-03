package teamzesa.command;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.announcer.ComponentHandler;

public class HealthSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);

        if (player == null)
            return false;

        player.setHealthScale(Double.parseDouble(args[1]));
        ComponentHandler.playerAnnouncer(
                player,args[0] + "님의 체력이" + args[1] + "로 설정됐습니다.", TextColor.color(255,255,0)
        );

        return true;
    }
}
