package teamzesa.command.UserCommand.Function;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.CommandRegisterSection;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.command.ListOfCommand;


public class ToggleFly extends CommandRegisterSection {

    public ToggleFly() {
        super(ListOfCommand.FLY);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player targetPlayer = (Player) sender;

        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());

        String comment = targetPlayer.getAllowFlight() ? "활성화" : "비활성화";
        playerSendMsgComponentExchanger(targetPlayer, "플라이 " + comment, ColorType.YELLOW);
        return true;
    }
}