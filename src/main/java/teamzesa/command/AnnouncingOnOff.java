package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

public class AnnouncingOnOff extends CommandRegisterSection {

    public AnnouncingOnOff() {
        super(CommandExecutorMap.ANNOUNCING);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        User targetUser = new UserController().readUser(((Player) commandSender).getUniqueId());

        String comment = targetUser.isAnnouncing() ? "비활성화" : "활성화";
        playerSendMsgComponentExchanger(commandSender, "공지 " + comment + " 완료", ColorMap.YELLOW);

        new UserBuilder(targetUser)
                .isAnnouncing(!targetUser.isAnnouncing())
                .buildAndUpdate();
        return true;
    }
}
