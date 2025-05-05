package teamzesa.command.ModeratorCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.Data.User.UserKillStatus.KillStatusBuilder;
import teamzesa.Data.User.UserKillStatus.KillStatusController;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;
import teamzesa.Enumeration.Command.ListOfCommand;
import teamzesa.command.CommandRegisterSection;

public class SetKillCount extends CommandRegisterSection {
    private boolean consoleSend = false;

    public SetKillCount() {
        super(ListOfCommand.KILL_COUNT_SET);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        UserKillStatus targetUser = null;
        try {
            targetUser = new KillStatusController().readUser(Bukkit.getPlayer(strings[0]).getUniqueId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new KillStatusBuilder(targetUser)
                .killCount(Integer.parseInt(strings[1]))
                .buildAndUpdate();

        return true;
    }
}
