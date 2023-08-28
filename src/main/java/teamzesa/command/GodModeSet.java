package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;

public class GodModeSet extends ComponentExchanger implements CommandExecutor {
    private UserMapHandler userMapHandler;

    public GodModeSet() {
        userMapHandler = UserMapHandler.getUserHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            componentSet("/god [플레이어 이름]",Color.RED);
            return false;
        }

        UserHandler targetUser = new UserHandler(userMapHandler.getUser(args[0]));
        targetUser.setGodMode(!targetUser.isGodMode());

        String status = targetUser.isGodMode() ? "신" : "인간";
        String mention = "은 이제 " + status + " 입니다.";

        playerAnnouncer((Player)sender,args[0] + " 님" + mention,Color.yellow);

        Player targetPlayer = Bukkit.getPlayer(targetUser.getUser().getUuid());
        if (targetPlayer != null)
            playerAnnouncer(targetPlayer,"당신" + mention, Color.ORANGE);

        return true;
    }
}
