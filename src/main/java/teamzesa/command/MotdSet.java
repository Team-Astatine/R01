package teamzesa.command;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.announcer.ComponentExchanger;

public class MotdSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ComponentExchanger.componentSet("해당 명령어는 플레이어가 사용할 수 없습니다.", TextColor.color(0xF80040));
            return false;
        }

        Bukkit.motd(ComponentExchanger.componentSet(args[0]));
        Bukkit.getLogger().info(args[0] + "로 MOTD 재설정");
        return true;
    }
}
