package teamzesa.Event.UserInterface.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.UserInterface.TPA.TpaUI;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

public class TpaTabOpen extends CommandRegisterSection {

    public TpaTabOpen() {
        super(ListOfCommand.TPA_TAB_OPEN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

//        new TpaUI((Player) commandSender);
        commandSender.sendMessage(componentExchanger("준비중입니다.", ColorType.RED));
        return true;
    }
}
