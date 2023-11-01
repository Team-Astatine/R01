package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.UserIOHandler;
import teamzesa.dataValue.ColorList;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.resgisterEvent.EventExecutor;


public class SaveUserData implements CommandExecutor, EventExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            ComponentExchanger.playerAnnouncer(sender, "권한이 없습니다.", ColorList.RED);
            return false;
        }

        UserIOHandler.getIOHandler().exportUserData();
        sendComment(sender, "Success to saving UserData");
        return true;
    }

    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            ComponentExchanger.playerAnnouncer(sender,comment, ColorList.YELLOW);
        else Bukkit.getLogger().info("[R01] " + comment);
    }
}
