package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.RObjectIOHandler;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.UserKillStatus;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.DataBase.entity.User;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.DataFile;

import java.util.Optional;


public class SaveR01ObjectData extends CommandRegisterSection {
    private Player senderPlayer;
    private boolean isConsoleSend = false;

    public SaveR01ObjectData() {
        super(CommandExecutorMap.SAVE_R01_OBJECT_DATA);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String label, String[] args) {
        User senderUser = new UserController().readUser(sender.getName());
        Optional.ofNullable(senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                ()        -> this.isConsoleSend = true
        );

        if (senderUser != null && !this.senderPlayer.isOp()) {
            playerSendMsgComponentExchanger(this.senderPlayer,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
            return false;
        }

        new RObjectIOHandler().exportData(
                DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName()
        );

        new RObjectIOHandler().exportData(
                DataFile.KILL_STATUS, new KillStatusController().getAllUserTable(), getClass().getName()
        );
/*
        if (args == null || args[0].isEmpty()) {
            this.rObjectIOHandler.exportData(DataFile.USER_DATA, getClass().getName(),
                    new UserController().getAllUserTable());

            this.rObjectIOHandler.exportData(DataFile.KILL_STATUS, getClass().getName(),
                    new KillStatusController().getAllUserTable());
        }

        else if (args[0].equalsIgnoreCase("data"))
            this.rObjectIOHandler.exportData(DataFile.USER_DATA, getClass().getName(),
                    new UserController().getAllUserTable());

        else if (args[0].equalsIgnoreCase("killStatus"))
            this.rObjectIOHandler.exportData(DataFile.KILL_STATUS, getClass().getName(),
                    new KillStatusController().getAllUserTable());
*/
        sendComment("Success to exporting UserData");
        return true;
    }

    private void sendComment(String comment) {
        if (isConsoleSend && this.senderPlayer == null)
            Bukkit.getLogger().info("[R01] " + comment);
        else playerSendMsgComponentExchanger(this.senderPlayer, comment, ColorMap.YELLOW);
    }
}
