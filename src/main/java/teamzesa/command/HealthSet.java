package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;
import teamzesa.event.EventExecutor;

import java.util.Optional;


public class HealthSet extends ComponentExchanger implements CommandExecutor, EventExecutor {
    private Player targetPlayer;
    private Player senderPlayer;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (args.length < 2) {
            if (sender instanceof Player)
                playerSendMsgComponentExchanger(sender,"/sethealth [닉네임] [체력값]",ColorList.RED);
            else
                Bukkit.getLogger().info("[R01] /sethealth [닉네임] [체력값]");
            return false;
        }

        Optional.ofNullable(Bukkit.getPlayer(args[0])).ifPresent(
                player -> {
                    this.targetPlayer = player;
                    this.senderPlayer = (Player)sender;

                    setPlayerHealth(Double.parseDouble(args[1]));
                }
            );

        return true;
    }

    private void setPlayerHealth(double setHealthValue) {
        UserController userController = new UserController();
        userController.healthUpdate(
                new UserBuilder(userController.readUser(this.targetPlayer))
                        .healthScale(setHealthValue)
                        .buildAndUpdate()
        );

        playerSendMsgComponentExchanger(
                this.targetPlayer,
                this.targetPlayer.getName() + "님의 체력이" + setHealthValue + "으로 설정됐습니다.",
                ColorList.YELLOW);

        playerSendMsgComponentExchanger(
                this.senderPlayer,
                this.targetPlayer.getName() + "님의 체력이" + setHealthValue + "으로 설정됐습니다.",
                ColorList.YELLOW);
    }
}
