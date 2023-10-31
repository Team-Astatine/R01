package teamzesa.command;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.resgisterEvent.EventExecutor;

import java.util.Optional;


public class GodModeSet implements CommandExecutor, EventExecutor {
    private Player targetPlayer;
    private UserHandler targetUser;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
//        콘솔로 입력 시 return
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("[R01] 유저 상태로 입력해주세요.");
            return false;
        }

        Optional<Player>checkingPlayer = Optional.ofNullable(Bukkit.getPlayer(args[0]));
        if (checkingPlayer.isEmpty()) {
            ComponentExchanger.playerAnnouncer(sender, "/god [플레이어 이름]", ColorList.RED);
            return false;
        } else setFieldVariable(checkingPlayer.get());

        setPlayerGodMode();
        sendCommentSendUser(sender);
        return true;
    }

    private void setFieldVariable(@NotNull Player player) {
        this.targetPlayer = player;
        this.targetUser = new UserHandler(UserMapHandler.getUserMapHandler().getUser(player));
    }

    private void setPlayerGodMode() {
        this.targetUser.setGodMode(!this.targetUser.isGodMode());
        this.targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,100000000,0));
    }

    private void sendCommentSendUser(CommandSender sender) {
        if (!this.targetPlayer.equals(sender))
            ComponentExchanger.playerAnnouncer(sender, this.targetPlayer.getName() + getGodModStatus(), ColorList.ORANGE);
        ComponentExchanger.playerAnnouncer(this.targetPlayer, "당신" + getGodModStatus(), ColorList.ORANGE);
    }

    private @NotNull String getGodModStatus() {
        return "은 이제 " + (this.targetUser.isGodMode() ? "신" : "인간") + " 입니다.";
    }
}
