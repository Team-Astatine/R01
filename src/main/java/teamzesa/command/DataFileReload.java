package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.entity.User;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.R01;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;


public class DataFileReload extends CommandRegisterSection {

    private Player player;
    private boolean isConsoleSend = false;

    public DataFileReload() {
        super(CommandExecutorMap.CONFIG_RELOAD);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        User user = new UserController().readUser(sender.getName());
        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> this.player = Bukkit.getPlayer(existUser.uuid()),
                ()        -> this.isConsoleSend = true
        );

//        operation Check
        if (!player.isOp() && !this.isConsoleSend) {
            playerSendMsgComponentExchanger(player,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        sendComment();
        R01.getPlugin(R01.class).configFileLoader();
        return true;
    }

    private void sendComment() {
        String comment = "Reload Done";
        if (this.isConsoleSend)
            Bukkit.getLogger().info("[R01] " + comment);
        else playerSendMsgComponentExchanger(this.player, comment, ColorList.YELLOW);
    }
}
