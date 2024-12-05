package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandType;


public class Fly extends CommandRegisterSection {

    public Fly() {
        super(CommandType.FLY);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player targetPlayer = (Player) sender;

        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());

        String comment = targetPlayer.getAllowFlight() ? "활성화" : "비활성화";
        playerSendMsgComponentExchanger(targetPlayer, "플라이 " + comment, ColorList.YELLOW);
        return true;
    }
}