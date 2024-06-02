package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.DataBase.entity.User;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;

import java.util.Optional;


public class SetHealth extends CommandRegisterSection {
    private Player senderPlayer;
    private Player targetPlayer;
    private boolean isConsoleSend = false;

    public SetHealth() {
        super(CommandExecutorMap.HEALTH_RESET);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        User senderUser = new UserController().readUser(sender.getName());
        Optional.ofNullable(senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                ()        -> this.isConsoleSend = true
        );

        if (senderUser != null && !this.senderPlayer.isOp()) {
            playerSendMsgComponentExchanger(this.senderPlayer,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
            return false;
        }

        Optional.ofNullable(Bukkit.getPlayer(args[0])).ifPresent(
                player -> {
                    this.targetPlayer = player;
                    setPlayerHealth(Double.parseDouble(args[1]));
                }
            );
        return true;
    }

    private void setPlayerHealth(double setHealthValue) {
        UserController userController = new UserController();
        userController.healthUpdate(
                new UserBuilder(userController.readUser(this.targetPlayer.getUniqueId()))
                        .healthScale(setHealthValue)
                        .buildAndUpdate()
        );

        sendComment(setHealthValue);
    }

    private void sendComment(double setHealthValue) {
        String comment = this.targetPlayer.getName() + "님의 체력이" + setHealthValue + "으로 설정됐습니다.";
        if (isConsoleSend) {
            Bukkit.getLogger().info("[R01] " + comment);
            return;
        }

        if (!this.senderPlayer.equals(this.targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, comment, ColorMap.YELLOW);
        playerSendMsgComponentExchanger(this.targetPlayer, comment, ColorMap.YELLOW);
    }
}
