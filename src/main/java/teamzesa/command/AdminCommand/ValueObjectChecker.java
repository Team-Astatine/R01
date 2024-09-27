package teamzesa.command.AdminCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandType;

import java.util.Optional;

public class ValueObjectChecker extends CommandRegisterSection {

    public ValueObjectChecker() {
        super(CommandType.USER_OBJECT_CHECKER);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        User user = null;
        try {
            user = new UserController().readUser(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user)
                .ifPresentOrElse(
                        existUser -> {
                            UserKillStatus userKillStatus = new KillStatusController().readUser(existUser.uuid());
                            sendComment(sender, existUser + "\n\n" + userKillStatus);
                        },

                        () -> {
                            sendComment(sender, "존재하지 않는 유저");
                        }
                );

        return true;
    }

    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            playerSendMsgComponentExchanger(sender, comment, ColorList.YELLOW);
        else Bukkit.getLogger().info(comment);
    }
}
