package teamzesa.command;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.ColorList;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;


public class God extends CommandRegisterSection {
    private User senderUser;
    private Player senderPlayer;
    private boolean isConsoleSend = false;

    public God() {
        super(CommandExecutorMap.GOD_MODE);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        this.senderUser = new UserController().readUser(sender.getName());
        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                ()        -> this.isConsoleSend = true
        );

        if (this.senderUser != null && !this.senderPlayer.isOp()) {
            playerSendMsgComponentExchanger(this.senderPlayer,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            sendComment(this.senderPlayer ,"해당 유저는 존재하지 않습니다.", ColorList.RED);
            return false;
        }

        changeUserStatus(targetPlayer);
        isConsoleSend = false;
        return true;
    }

    private void changeUserStatus(Player targetPlayer) {
        User targetUser = new UserController().readUser(targetPlayer);
        targetUser = new UserBuilder(targetUser)
                .godMode(!targetUser.isGodMode())
                .buildAndUpdate();
        setPotionEffect(targetPlayer,targetUser);


        String targetStatus = targetUser.isGodMode() ? "신" : "인간";
        String comment = "은(는) 이제 " + targetStatus + " 입니다.";

        if (isConsoleSend)
            Bukkit.getLogger().info("[R01] " + targetUser.name() + comment);

        else if (!this.senderPlayer.equals(targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, targetUser.name() + comment, ColorList.ORANGE);

        playerSendMsgComponentExchanger(targetPlayer, "당신" + comment, ColorList.ORANGE);

    }

    private void sendComment(Player senderPlayer, String comment, ColorList color) {
        if (this.isConsoleSend)
            Bukkit.getLogger().info("[R01] " + comment);
        else playerSendMsgComponentExchanger(senderPlayer, comment, color);
    }

    public void setPotionEffect(Player targetPlayer, User targetUser) {
        if (targetUser.isGodMode()) {
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,100000000,0));
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,100000000,0));
        } else {
            targetPlayer.removePotionEffect(PotionEffectType.SATURATION);
            targetPlayer.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }
}
