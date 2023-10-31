package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.R01;
import teamzesa.dataValue.ColorList;
import teamzesa.resgisterEvent.EventExecutor;


public class Reload implements CommandExecutor, EventExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player)sender;
        if (!player.isOp()) {
            ComponentExchanger.playerAnnouncer(player,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        R01.getPlugin(R01.class).fileLoader();
        ComponentExchanger.playerAnnouncer(player,"Reload Done", ColorList.YELLOW);
        return true;
    }
}
