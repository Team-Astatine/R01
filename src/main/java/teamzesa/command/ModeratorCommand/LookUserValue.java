package teamzesa.command.ModeratorCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.CommandType;

import java.util.Optional;

public class LookUserValue extends CommandRegisterSection {

    public LookUserValue() {
        super(CommandType.LOOK_USER_VALUE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String @NotNull [] args) {

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
