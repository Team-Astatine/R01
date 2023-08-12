package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.userValue.User;
import teamzesa.userValue.UserHandler;

import java.awt.*;

public class VoChecker extends ComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        User user = UserHandler
                .getUserHandler()
                .getUser(Bukkit.getPlayer(args[0]));

        playerAnnouncer((Player) sender,user.toString(), Color.YELLOW);
        return true;
    }
}
