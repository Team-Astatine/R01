package teamzesa.Event.UserInterface.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.Event.UserInterface.GSit.GSitUI;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

public class GSitTabOpen extends CommandRegisterSection {

    public GSitTabOpen() {
        super(ListOfCommand.GSIT_TAB_OPEN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        new GSitUI((Player) commandSender);
        return true;
    }
}
