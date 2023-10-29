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
        Player player = (Player) sender;

        player.setAllowFlight(!player.getAllowFlight());
        String announcer = player.getAllowFlight() ? "활성화" : "비활성화";
        ComponentExchanger.playerAnnouncer(player,"플라이 " + announcer, ColorList.YELLOW);
        return true;
    }
}
