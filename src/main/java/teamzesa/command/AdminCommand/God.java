package teamzesa.command.AdminCommand;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;

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
        try {
            this.senderUser = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                ()        -> this.isConsoleSend = true
        );

        if (this.senderUser != null && !this.senderPlayer.isOp()) {
            playerSendMsgComponentExchanger(this.senderPlayer,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            String comment = "해당 유저는 존재하지 않습니다.";

            if (this.isConsoleSend)
                Bukkit.getLogger().info("[R01] " + comment);
            else playerSendMsgComponentExchanger(senderPlayer, comment, ColorMap.RED);
            return false;
        }

        changeUserStatus(targetPlayer);
        isConsoleSend = false;
        return true;
    }


    private void changeUserStatus(Player targetPlayer) {
        User targetUser = new UserController().readUser(targetPlayer.getUniqueId());
        targetUser = new UserBuilder(targetUser)
                .isGodMode(!targetUser.isGodMode())
                .buildAndUpdate();
        setPotionEffect(targetPlayer,targetUser);


        String targetStatus = targetUser.isGodMode() ? "신" : "인간";
        String comment = "은(는) 이제 " + targetStatus + " 입니다.";

        if (isConsoleSend)
            Bukkit.getLogger().info("[R01] " + targetUser.nameList().getLast() + comment);

        else if (ObjectUtils.notEqual(this.senderPlayer, targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, targetUser.nameList().getLast() + comment, ColorMap.ORANGE);

        playerSendMsgComponentExchanger(targetPlayer, "당신" + comment, ColorMap.ORANGE);

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
