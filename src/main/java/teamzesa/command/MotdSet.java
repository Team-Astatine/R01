package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;

import java.awt.*;

public class MotdSet extends ComponentExchanger implements CommandExecutor {

    private ConfigIOHandler configIOHandler;

    public MotdSet() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player)sender;
        if (!player.getName().equals("JAXPLE")) {
            componentSet("해당 명령어는 플레이어가 사용할 수 없습니다.", Color.RED);
            return false;
        }

        StringBuilder customMotd = new StringBuilder();
        for (String motd : args)
            customMotd.append(motd)
                      .append(" ");

        String finalMotd = customMotd.toString().trim();
        configIOHandler.worldConfigSave(finalMotd);
        Bukkit.getLogger().info("Motd Set > " + finalMotd);
        Bukkit.motd(componentSet(customMotd.toString()));
        playerAnnouncer(player,finalMotd + "로 변경 됐습니다.",Color.YELLOW);
        return true;
    }
}
