package teamzesa.Event.UserInterface.Command;

import org.bukkit.Bukkit;
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

        if (Bukkit.getOnlinePlayers().size() == 1) {
            commandSender.sendMessage(componentExchanger("온라인 플레이어가 없습니다.", ColorType.RED));
            return false;
        }

        new TpaUI((Player) commandSender);
        return true;
    }
}
