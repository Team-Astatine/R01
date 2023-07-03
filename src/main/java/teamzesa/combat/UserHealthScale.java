package teamzesa.combat;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.announcer.ComponentExchanger;
import teamzesa.userValue.UserHandler;

public class UserHealthScale implements Listener {
    private final double MAX_HEALTH_SCALE = 60.0;
    private final Double MIN_HEALTH_SCALE = 4.0;
    private final Double STEP_SIZE = 2.0;
    private final UserHandler userHandler;

    public UserHealthScale() {
        userHandler = UserHandler.getUserHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Player killer = killed.getKiller();

        if (killer == null)
            return;

        if (killed.getHealthScale() <= MIN_HEALTH_SCALE)
            return;

        if (killer.getHealthScale() >= MAX_HEALTH_SCALE)
            return;

        if (killed == killer) {
            return;
        }

        talking(killed,killer);

        double killedHealth = killed.getHealthScale() - STEP_SIZE;
        double killerHealth = killer.getHealthScale() + STEP_SIZE;

        userHandler.updateUser(killed.getUniqueId(),killedHealth);
        userHandler.updateUser(killer.getUniqueId(),killerHealth);
    }

    public void talking(Player killed, Player killer) {
        ComponentExchanger.playerAnnouncer(
                killed,killer.getName() + "님이 체력을 약탈했습니다.", TextColor.color(0xF80040));
        ComponentExchanger.playerAnnouncer(
                killer,killed.getName() + "님이 체력을 약탈했습니다.", TextColor.color(0xF80040));
    }
}
