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

public class GodModeSet implements CommandExecutor {
    private UserHandler userHandler;

    public GodModeSet() {
        userHandler = UserHandler.getUserHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = userHandler.getPlayer(args[0]);
        User user = userHandler.getUser(player.getUniqueId());
        String mention = null;

        user.setGodMode(!user.isGodMode());
        userHandler.updateUser(user);

        mention = user.isGodMode() ? "당신은 이제 신 입니다." : "당신은 이제 인간 입니다.";
        player.sendMessage(ComponentExchanger.componentSet(mention,"ORANGE"));

        return false;
    }
}
