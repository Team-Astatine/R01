package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.UserHandler.UserBuilder;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.ListOfCommand;

public class ToggleAnnouncing extends CommandRegisterSection {

    public ToggleAnnouncing() {
        super(ListOfCommand.ANNOUNCING);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        User targetUser = new UserController().readUser(((Player) commandSender).getUniqueId());

        String comment = targetUser.announcingSkip() ? "비활성화" : "활성화";
        playerSendMsgComponentExchanger(commandSender, "공지 " + comment + " 완료", ColorList.YELLOW);

        new UserBuilder(targetUser)
                .isAnnouncingSkip(!targetUser.announcingSkip())
                .buildAndUpdate();
        return true;
    }
}