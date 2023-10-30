package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.dataValue.ColorList;


public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player)sender;
        if (!player.isOp()) {
            ComponentExchanger.playerAnnouncer(player,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        configIOHandler.fileLoader();
        configIOHandler.allConfigLoad();

        ComponentExchanger.playerAnnouncer(player,"Reload Done", ColorList.YELLOW);
        return true;
    }
}
