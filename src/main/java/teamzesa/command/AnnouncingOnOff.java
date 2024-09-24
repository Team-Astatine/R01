package teamzesa.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.util.Enum.ColorList;

public record AnnouncingOnOff() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        User targetUser = new UserController().readUser(((Player) commandSender).getUniqueId());
        String comment = targetUser.announcingSkip() ? "비활성화" : "활성화";

        commandSender.sendMessage(Component.text(
                "공지 " + comment + " 완료", ColorList.YELLOW.getTextColor())
        );

        new UserBuilder(targetUser)
                .isAnnouncingSkip(!targetUser.announcingSkip())
                .buildAndUpdate();
        return true;
    }
}
