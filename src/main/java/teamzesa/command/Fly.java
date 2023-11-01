package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;
import teamzesa.resgisterEvent.EventExecutor;


public class Fly implements CommandExecutor, EventExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player targetPlayer = (Player) sender;

        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());
        String comment = targetPlayer.getAllowFlight() ? "활성화" : "비활성화";
        sendComment(sender,"플라이 " + comment);
        return true;
    }
    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            ComponentExchanger.playerAnnouncer(sender, comment, ColorList.YELLOW);
        else Bukkit.getLogger().info("[R01] " + sender.getName() + comment);
    }
}
