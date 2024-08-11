package teamzesa.command.AdminCommand;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.R01;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.userHandler.UserController;

import java.util.Optional;


public class DataFileReload extends CommandRegisterSection {

    private Player player;
    private boolean isConsoleSend = false;

    public DataFileReload() {
        super(CommandExecutorMap.CONFIG_RELOAD);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        User user = null;
        try {
            user = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> this.player = Bukkit.getPlayer(existUser.uuid()),
                () -> this.isConsoleSend = true
        );

//        operation Check
        if (BooleanUtils.isFalse(player.isOp()) && BooleanUtils.isFalse(this.isConsoleSend)) {
            playerSendMsgComponentExchanger(player, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
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
        else playerSendMsgComponentExchanger(this.player, comment, ColorMap.YELLOW);
    }
}
