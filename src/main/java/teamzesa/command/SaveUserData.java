package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.UserIOHandler;
import teamzesa.dataValue.ColorList;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.resgisterEvent.EventExecutor;


public class SaveUserData implements CommandExecutor, EventExecutor {
    private static UserIOHandler userIoHandler;
    private static UserMapHandler userMapHandler;

    public SaveUserData() {
        userMapHandler = UserMapHandler.getUserMapHandler();
        userIoHandler = UserIOHandler.getIOHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ComponentExchanger.playerAnnouncer((Player)sender, "권한이 없습니다.", ColorList.RED);
            return false;
        }

        userIoHandler.exportUserData();
        Bukkit.getLogger().info("Success to saving UserData");
        return true;
    }
}
