package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;

import java.awt.*;

public class MotdSet extends ComponentExchanger implements CommandExecutor {

    private ConfigIOHandler configIOHandler;

    public MotdSet() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (!player.getName().equals("JAXPLE")) {
            componentSet("해당 명령어는 플레이어가 사용할 수 없습니다.", Color.RED);
            return false;
        }

        StringBuilder customMotd = new StringBuilder();
        for (String motd : args)
            customMotd.append(motd)
                      .append(" ");

        configIOHandler.worldConfigSave(customMotd);
        Bukkit.getLogger().info("Motd Set :: " + customMotd.toString().trim());
        Bukkit.motd(componentSet(customMotd.toString()));
        player.sendMessage(
                componentSet(customMotd.append(" 로 변경 됐습니다.").toString(),Color.YELLOW));
        return true;
    }
}
