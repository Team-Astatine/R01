package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusIOHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.Optional;

public class SaveKillStatusData extends CommandRegisterSection {
    private Player senderPlayer;
    private boolean isConsoleSend = false;

    public SaveKillStatusData() {
        super(CommandExecutorMap.SAVE_KILL_STATUS_DATA);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player)
            this.senderPlayer = player;
        else isConsoleSend = true;

        if (this.senderPlayer != null && !this.senderPlayer.isOp() && !isConsoleSend) {
            playerSendMsgComponentExchanger(this.senderPlayer,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
            return false;
        }

        sendComment("Success to saving KillStatusData");
        return true;
    }

    private void sendComment(String comment) {
        if (isConsoleSend && this.senderPlayer == null)
            Bukkit.getLogger().info("[R01] " + comment);
        else playerSendMsgComponentExchanger(this.senderPlayer, comment, ColorMap.YELLOW);
        KillStatusIOHandler.exportKillStatusData("Using Save User Command");
    }
}
