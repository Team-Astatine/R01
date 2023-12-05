package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.R01;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;

public class Show extends ComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player))
            return false;

        if (!player.isOp())
            playerSendMsgComponentExchanger(player,"권한이 없습니다.", ColorList.RED);

        player.showPlayer(R01.getPlugin(R01.class), player);

        return false;
    }
}
