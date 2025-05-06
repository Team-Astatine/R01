package teamzesa.command.UserCommand.Function;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.Event.PlayerInteraction.Announce.Tip.Announcer;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

public class PlayTime extends CommandRegisterSection {

    public PlayTime() {
        super(ListOfCommand.TIME);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Announcer.getAnnouncer().joinPlayerInformationAnnouncer((Player) commandSender);
        return true;
    }
}
