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
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.resgisterEvent.EventExecutor;

import java.awt.*;

public class GodModeSet implements CommandExecutor, EventExecutor {
    private UserMapHandler userMapHandler;

    public GodModeSet() {
        userMapHandler = UserMapHandler.getUserHandler();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            ComponentExchanger.componentSet("/god [플레이어 이름]",Color.RED);
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        UserHandler targetUser = new UserHandler(userMapHandler.getUser(player));

        targetUser.setGodMode(!targetUser.isGodMode());
        player.addPotionEffect(
                new PotionEffect(PotionEffectType.SATURATION,100000000,0)
        );

        String status = targetUser.isGodMode() ? "인간" : "신";
        String mention = "은 이제 " + status + " 입니다.";

//        스스로에게 적용했을시 무시
        if (!sender.getName().equals(targetUser.getUser().getName()))
            ComponentExchanger.playerAnnouncer((Player)sender,args[0] + " 님" + mention,Color.yellow);

        Player targetPlayer = Bukkit.getPlayer(targetUser.getUser().getUuid());

        if (targetPlayer != null)
            ComponentExchanger.playerAnnouncer(targetPlayer,"당신" + mention, Color.ORANGE);

        return true;
    }
}
