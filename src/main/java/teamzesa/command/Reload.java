package teamzesa.command;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.dataValue.ColorList;

import java.io.File;

public class Reload implements CommandExecutor {
    private final File dataPathFile;
    private final ConfigIOHandler configIOHandler;

    public Reload(File file) {
        this.dataPathFile = file;
        this.configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player)sender;
        if (!player.isOp()) {
            ComponentExchanger.playerAnnouncer(player,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        this.configIOHandler.fileLoader(dataPathFile);
        this.configIOHandler.allConfigLoad();

        ComponentExchanger.playerAnnouncer(player,"Reload Done", ColorList.YELLOW);
        return true;
    }
}
