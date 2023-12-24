package teamzesa.command;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserMapHandler;
import teamzesa.event.EventExecutor;

import java.util.Optional;


public class GodModeSet extends ComponentExchanger implements CommandExecutor, EventExecutor {
    private Player targetPlayer;
    private User targetUser;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Optional<Player>checkingPlayer = Optional.ofNullable(Bukkit.getPlayer(args[0]));
        if (checkingPlayer.isEmpty()) {
            if (sender instanceof Player)
                playerSendMsgComponentExchanger(sender,"/god [플레이어 이름]",ColorList.RED);
            else
                Bukkit.getLogger().info("[R01] /god [플레이어 이름]");
            return false;
        }

        setFieldVariable(checkingPlayer.get());
        setPlayerGodMode();
        sendCommment(sender);
        return true;
    }

    private void sendCommment(@NotNull CommandSender sender) {
        boolean senderInstance = sender instanceof Player; //true == player , false == consol
        String comment = "은 이제 " + (this.targetUser.isGodMode() ? "신" : "인간") + " 입니다.";

        if (!this.targetPlayer.equals(sender) && senderInstance)
            playerSendMsgComponentExchanger(sender, this.targetPlayer.getName() + comment, ColorList.ORANGE);

        if (!senderInstance)
            Bukkit.getLogger().info("[R01] " + targetPlayer.getName() + comment);

        playerSendMsgComponentExchanger(this.targetPlayer, "당신" + comment, ColorList.ORANGE);
    }

    private void setFieldVariable(@NotNull Player player) {
        this.targetPlayer = player;
        this.targetUser = UserMapHandler.getUserMapHandler().getUser(player);
    }

    private void setPlayerGodMode() {
        this.targetUser.setGodMode(!this.targetUser.isGodMode());

        Runnable godModTask = this.targetUser.isGodMode()
            ? () -> this.targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,100000000,0))
            : () -> this.targetPlayer.removePotionEffect(PotionEffectType.SATURATION);
        godModTask.run();
    }
}
