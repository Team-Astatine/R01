package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserIOHandler;
import teamzesa.util.Enum.ColorList;


public class SaveUserData extends StringComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String label, String[] args) {
        UserIOHandler.exportUserData("Using Save User Command");
        sendComment(sender, "Success to saving UserData");
        return true;
    }

    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            playerSendMsgComponentExchanger(sender,comment, ColorList.YELLOW);
        else Bukkit.getLogger().info("[R01] " + comment);
    }
}
