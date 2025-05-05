package teamzesa.command.ModeratorCommand;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.Data.User.UserData.User;
import teamzesa.Data.User.UserData.UserBuilder;
import teamzesa.Data.User.UserData.UserController;
import teamzesa.command.CommandRegisterSection;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Enumeration.Command.ListOfCommand;

import java.util.Optional;


public class SetGodMode extends CommandRegisterSection {
    private User senderUser;
    private Player senderPlayer;
    private boolean consoleSend = false;

    public SetGodMode() {
        super(ListOfCommand.GOD_MODE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String @NotNull [] args) {

        try {
            this.senderUser = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        if (ObjectUtils.allNotNull(this.senderUser) && BooleanUtils.isFalse(this.senderPlayer.isOp())) {
            playerSendMsgComponentExchanger(this.senderPlayer, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorType.RED);
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (ObjectUtils.isEmpty(targetPlayer)) {
            String comment = "해당 유저는 존재하지 않습니다.";

            if (this.consoleSend)
                Bukkit.getLogger().info("[R01] " + comment);
            else playerSendMsgComponentExchanger(senderPlayer, comment, ColorType.RED);
            return false;
        }

        changeUserStatus(targetPlayer);
        consoleSend = false;
        return true;
    }


    private void changeUserStatus(Player targetPlayer) {
        User targetUser = new UserController().readUser(targetPlayer.getUniqueId());
        targetUser = new UserBuilder(targetUser)
                .isGodMode(!targetUser.godMode())
                .buildAndUpdate();
        setPotionEffect(targetPlayer, targetUser);


        String targetStatus = targetUser.godMode() ? "신" : "인간";
        String comment = "은(는) 이제 " + targetStatus + " 입니다.";

        if (consoleSend)
            Bukkit.getLogger().info("[R01] " + targetUser.nameList().getLast() + comment);

        else if (ObjectUtils.notEqual(this.senderPlayer, targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, targetUser.nameList().getLast() + comment, ColorType.ORANGE);

        playerSendMsgComponentExchanger(targetPlayer, "당신" + comment, ColorType.ORANGE);

    }

    public void setPotionEffect(Player targetPlayer, User targetUser) {
        if (targetUser.godMode()) {
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100000000, 0));
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000000, 0));
        } else {
            targetPlayer.removePotionEffect(PotionEffectType.SATURATION);
            targetPlayer.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }
}
