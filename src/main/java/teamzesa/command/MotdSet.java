package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;

public class MotdSet implements CommandExecutor {
    private FileConfiguration config;

    public MotdSet(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (!player.getName().equals("JAXPLE")) {
            ComponentExchanger.componentSet("해당 명령어는 플레이어가 사용할 수 없습니다.", "RED");
            return false;
        }

        StringBuilder customMotd = new StringBuilder();
        if (args.length > 1) {
            for (String motd : args)
                customMotd.append(motd);
        }

        config.set("motd",customMotd);
        player.sendMessage(
                ComponentExchanger.componentSet(customMotd.append(" 로 변경 됐습니다."),"YELLOW"));
        return true;
    }
}
