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
        Player player = Bukkit.getPlayer(args[0]);
        Optional.ofNullable(player)
                .ifPresentOrElse(
                    existPlayer -> sendComment(sender, existPlayer),
                    () ->        sendComment(sender, null)
                );

        return true;
    }

    private void sendComment(CommandSender sender, Player player) {
        User user = new UserController().readUser(player.getUniqueId());
        UserKillStatus userKillStatus = new KillStatusController().readUser(player.getUniqueId());

        if (sender instanceof Player)
            playerSendMsgComponentExchanger(player, user + " " + userKillStatus, ColorMap.YELLOW);
        else Bukkit.getLogger().info(user + " " + userKillStatus);
    }
}
