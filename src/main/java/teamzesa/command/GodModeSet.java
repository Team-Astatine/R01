package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.userValue.User;
import teamzesa.userValue.UserHandler;

import java.awt.*;

public class GodModeSet extends ComponentExchanger implements CommandExecutor {
    private UserHandler userHandler;

    public GodModeSet() {
        userHandler = UserHandler.getUserHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            componentSet("/god [플레이어 이름]",Color.RED);
            return false;
        }

        Player commandSender = (Player) sender;
        User targetUser = userHandler.getUser(args[0]);

        targetUser.setGodMode(!targetUser.isGodMode());
        String status = targetUser.isGodMode() ? "신" : "인간";
        sender.sendMessage(componentSet(args[0] + " 님은 이제 " + status + " 입니다."));

        Player targetPlayer = Bukkit.getPlayer(targetUser.getUuid());
        if (targetPlayer == null)
            commandSender.sendMessage(componentSet("해당 플레이어는 접속하지 않았습니다.",Color.RED));
        else
            targetPlayer.sendMessage(componentSet("당신은 이제 " + status + " 입니다.", Color.ORANGE));

        userHandler.updateUser(targetUser);
        return true;
    }
}
