package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.UserKillStatus;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.entity.User;
import teamzesa.DataBase.userHandler.UserController;

import java.util.Optional;
import java.util.UUID;

public class ValueObjectChecker extends StringComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        User user = new UserController().readUser(args[0]);
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
            playerSendMsgComponentExchanger(sender, comment, ColorMap.YELLOW);
        else Bukkit.getLogger().info(comment);
    }
}
