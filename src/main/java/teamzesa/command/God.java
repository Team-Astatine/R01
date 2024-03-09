package teamzesa.command;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegister;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.ColorList;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;


public class God extends CommandRegister {
    private User senderUser;
    private Player senderPlayer;
    private boolean consoleSender = false;

    public God() {
        super(CommandExecutorMap.GOD_MODE);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        this.senderUser = new UserController().readUser(sender.getName());
        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                ()        -> this.consoleSender = true
        );

        System.out.println("Operation Check");
        if (!this.senderPlayer.isOp() && this.senderUser != null) {
            playerSendMsgComponentExchanger(senderPlayer,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        System.out.println("Target Player Check");
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            sendComment("해당 유저는 존재하지 않습니다.", ColorList.RED);
            return false;
        }

        System.out.println("Change User Status");
        User targetUser = new UserController().readUser(targetPlayer);
        targetUser = new UserBuilder(targetUser)
                .godMode(!targetUser.isGodMode())
                .buildAndUpdate();
        setPotionEffect(targetPlayer,targetUser);

        String targetName = this.senderUser.equals(targetUser) ? "당신" : targetUser.name();
        String targetStatus = targetUser.isGodMode() ? "신" : "인간";
        sendComment(
                targetName + "은 이제 " + targetStatus + " 입니다."
                , ColorList.ORANGE);

        return true;
    }

    private void sendComment(String comment, ColorList color) {
        if (this.consoleSender)
            Bukkit.getLogger().info("[R01] " + comment);
        else playerSendMsgComponentExchanger(this.senderPlayer, comment, color);
    }

    public void setPotionEffect(Player player, User user) {
        if (user.isGodMode()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,100000000,0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,100000000,0));
        } else {
            player.removePotionEffect(PotionEffectType.SATURATION);
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }
}
