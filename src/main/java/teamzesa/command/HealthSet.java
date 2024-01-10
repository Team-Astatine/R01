package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;
import teamzesa.event.EventExecutor;

import java.util.Optional;


public class HealthSet extends ComponentExchanger implements CommandExecutor, EventExecutor {
    private Player targetPlayer;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (args.length < 2) {
            if (sender instanceof Player)
                playerSendMsgComponentExchanger(sender,"/체력초기화 [닉네임] [체력값]",ColorList.RED);
            else
                Bukkit.getLogger().info("[R01] /체력초기화 [닉네임] [체력값]");
            return false;
        }

//        gpt
        Optional.ofNullable(Bukkit.getPlayer(args[0])).ifPresent(optionalPlayer -> {
            this.targetPlayer = optionalPlayer;
            Optional.of(args[1])
                    .map(Double::parseDouble)
                    .ifPresent(this::setPlayerHealth);
        });
        return true;
    }

    private void setPlayerHealth(double setHealthValue) {
        new UserBuilder(new UserController().readUser(this.targetPlayer))
                .healthScale(setHealthValue)
                .buildAndUpdate();

        playerSendMsgComponentExchanger(
                this.targetPlayer,
                this.targetPlayer.getName() + "님의 체력이" + setHealthValue + "으로 설정됐습니다.",
                ColorList.YELLOW);
    }
}
