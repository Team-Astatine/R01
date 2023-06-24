package teamzesa.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.user.IOHandler;

import java.io.File;

public class SaveUserData implements CommandExecutor {
    private static IOHandler ioHandler;
    private File userDataFile;

    public SaveUserData(File userDataFile) {
        ioHandler = IOHandler.getInstance();
        this.userDataFile = userDataFile;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "권한이 없습니다.");
            return false;
        }

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                ioHandler.outputUserData(userDataFile);
            }
        };

        return true;
    }
}
