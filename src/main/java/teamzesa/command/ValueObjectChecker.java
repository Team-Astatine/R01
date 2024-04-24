package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;

public class ValueObjectChecker extends StringComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Optional.ofNullable(new UserController().readUser(args[0]))
                .ifPresentOrElse(
                    existUser -> sendComment(sender, existUser),
                    () ->        sendComment(sender, null)
                );

        return true;
    }

    private void sendComment(CommandSender sender, User user) {
        String userInfo = user == null ? "존재하지 않는 유저" : user.toString();
        if (sender instanceof Player player)
            playerSendMsgComponentExchanger(player, userInfo, ColorMap.YELLOW);
        else Bukkit.getLogger().info(user.toString());
    }
}
