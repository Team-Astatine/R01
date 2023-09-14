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
    private final File dataPathFile;
    private final ConfigIOHandler configIOHandler;

    public Reload(File file) {
        dataPathFile = file;
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player)sender;
        if (!player.isOp()) {
            playerAnnouncer(player,"해당 명령어는 플레이어가 사용할 수 없습니다.", Color.RED);
            return false;
        }

        configIOHandler.fileLoader(dataPathFile);
        configIOHandler.allConfigLoad();

        playerAnnouncer(player,"Reload Done",Color.YELLOW);
        return true;
    }
}
