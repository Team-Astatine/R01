package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealthSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);

        if (player == null)
            return false;

        player.setHealthScale(Double.parseDouble(args[1]));
        sender.sendMessage(ChatColor.YELLOW + args[0] + "님의 체력이" + args[1] + "로 설정됐습니다.");
        return true;
    }
}
