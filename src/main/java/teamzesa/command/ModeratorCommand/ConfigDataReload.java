package teamzesa.command.ModeratorCommand;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.R01;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.ListOfCommand;

import java.util.Optional;


public class ConfigDataReload extends CommandRegisterSection {

    private Player player;
    private boolean consoleSend = false;

    public ConfigDataReload() {
        super(ListOfCommand.CONFIG_RELOAD);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        User user = null;
        try {
            user = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> this.player = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

//        operation Check
        if (BooleanUtils.isFalse(player.isOp()) && BooleanUtils.isFalse(this.consoleSend)) {
            playerSendMsgComponentExchanger(player, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        sendComment();
        R01.getPlugin(R01.class).configFileLoader();
        return true;
    }

    private void sendComment() {
        String comment = "Reload Done";
        if (this.consoleSend)
            Bukkit.getLogger().info("[R01] " + comment);
        else playerSendMsgComponentExchanger(this.player, comment, ColorList.YELLOW);
    }
}
