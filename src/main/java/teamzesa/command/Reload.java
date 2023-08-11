package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;

import java.awt.*;
import java.io.File;

public class Reload extends ComponentExchanger implements CommandExecutor {
    private File dataPathFile;
    private ConfigIOHandler configIOHandler;

    public Reload(File file) {
        dataPathFile = file;
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player)sender;
        if (!player.getName().equals("JAXPLE")) {
            player.sendMessage(componentSet("해당 명령어는 플레이어가 사용할 수 없습니다.", Color.RED));
            return false;
        }

        configIOHandler.fileLoader(dataPathFile);
        configIOHandler.worldConfigLoad();
        configIOHandler.getMineListConfig();
        configIOHandler.getDiscordConfig();
        configIOHandler.getMineListConfig();

        player.sendMessage(
                componentSet("Reload 완료"));
        return true;
    }
}
