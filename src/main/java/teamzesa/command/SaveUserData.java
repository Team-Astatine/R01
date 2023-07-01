package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.userValue.IOHandler;
import teamzesa.userValue.UserHandler;

import java.io.File;

public class SaveUserData implements CommandExecutor {
    private static IOHandler ioHandler;
    private static UserHandler userHandler;
    private final File userDataFile;

    public SaveUserData(File userDataFile) {
        ioHandler = IOHandler.getIOHandler();
        userHandler = UserHandler.getUserHandler();
        this.userDataFile = userDataFile;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendPlainMessage(ChatColor.RED + "권한이 없습니다.");
            return false;
        }

        userHandler.saveAllUserData();
        ioHandler.outputUserData(userDataFile);
        Bukkit.getLogger().info("Success to saving UserData");
        return true;
    }
}
