package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MotdSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("해당 명령어는 플레이어가 사용할 수 없습니다.");
            return false;
        }

        Bukkit.setMotd(args[0]);
        System.out.println(args[0] + "로 MOTD 재설정");
        return true;
    }
}
