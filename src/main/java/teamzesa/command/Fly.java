package teamzesa.command;

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
        ComponentExchanger.playerAnnouncer(targetPlayer,"플라이 " + comment, ColorList.YELLOW);
        return true;
    }
}
