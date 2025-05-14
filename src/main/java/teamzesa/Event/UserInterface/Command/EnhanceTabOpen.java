package teamzesa.Event.UserInterface.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.Event.UserInterface.Enhance.EnhanceUI;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

public class EnhanceTabOpen extends CommandRegisterSection {

    public EnhanceTabOpen() {
        super(ListOfCommand.ENHANCE_TAB_OPEN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        new EnhanceUI((Player) commandSender);
        return true;
    }
}
