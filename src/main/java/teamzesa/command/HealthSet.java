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
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserMapHandler;
import teamzesa.event.EventExecutor;

import java.util.Optional;


public class HealthSet implements CommandExecutor, EventExecutor {
    private Player targetPlayer;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {

        if (args.length < 2) {
            if (sender instanceof Player)
                ComponentExchanger.playerAnnouncer(sender,"/체력초기화 [닉네임] [체력값]",ColorList.RED);
            else
                Bukkit.getLogger().info("[R01] /체력초기화 [닉네임] [체력값]");
            return false;
        }

//        gpt
        Optional.ofNullable(Bukkit.getPlayer(args[0]))
                .ifPresent(optionalPlayer -> {
                            this.targetPlayer = optionalPlayer;
                            Optional.of(args[1])
                                    .map(Double::parseDouble)
                                    .ifPresent(this::setPlayerHealth);
                        });

//        Optional<Player> player = Optional.ofNullable(Bukkit.getPlayer(args[0]));
//        player.ifPresent(optionalPlayer -> this.targetPlayer = optionalPlayer);

//        Optional<Double> targetPlayerHealth = Optional.of(Double.parseDouble(args[1]));
//        targetPlayerHealth.ifPresent(this::setPlayerHealth);

        updatePlayerInfo();
        return true;
    }

    private void updatePlayerInfo() {
        UserMapHandler userMapHandler = UserMapHandler.getUserMapHandler();
        userMapHandler.updateUser(this.targetPlayer.getUniqueId(), this.targetPlayer.getHealthScale());
    }

    private void setPlayerHealth(double setHealthValue) {
        this.targetPlayer.setHealthScale(setHealthValue);
        this.targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 40, 1));
        this.targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(setHealthValue);
        ComponentExchanger.playerAnnouncer(
                this.targetPlayer,
                this.targetPlayer.getName() + "님의 체력이" + setHealthValue + "으로 설정됐습니다.",
                ColorList.YELLOW);
    }
}
