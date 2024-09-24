package teamzesa.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Enum.ColorList;


public record Fly() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player targetPlayer = (Player) sender;
        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());

        String comment = targetPlayer.getAllowFlight() ? "활성화" : "비활성화";
        targetPlayer.sendMessage(Component.text("플라이 " + comment, ColorList.YELLOW.getTextColor()));
        return true;
    }
}
