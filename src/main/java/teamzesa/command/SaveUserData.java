package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.UserIOHandler;
import teamzesa.dataValue.userData.UserHandler;

import java.awt.*;

public class SaveUserData extends ComponentExchanger implements CommandExecutor {
    private static UserIOHandler userIoHandler;
    private static UserHandler userHandler;

    public SaveUserData() {
        userHandler = UserHandler.getUserHandler();
        userIoHandler = UserIOHandler.getIOHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            playerAnnouncer((Player)sender, "권한이 없습니다.", Color.RED);
            return false;
        }

        userIoHandler.outputUserData();
        Bukkit.getLogger().info("Success to saving UserData");
        return true;
    }
}
