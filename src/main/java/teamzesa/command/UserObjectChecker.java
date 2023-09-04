package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;

public class UserObjectChecker extends ComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        User user = UserMapHandler
                .getUserHandler()
                .getUser(Bukkit.getPlayer(args[0]));

        Bukkit.getLogger().info(user.toString());
        playerAnnouncer((Player) sender,user.toString(), Color.YELLOW);
        return true;
    }
}
