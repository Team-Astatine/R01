package teamzesa.combat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.userValue.UserHandler;

public class UserHealthScaleHandler implements Listener {
    private final double MAX_HEALTH_SCALE = 60.0;
    private final Double MIN_HEALTH_SCALE = 4.0;
    private final Double STEP_SIZE = 2.0;
    private final UserHandler userHandler;

    public UserHealthScaleHandler() {
        userHandler = UserHandler.getUserHandler();
    }

    @EventHandler
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

        killed.setHealthScale(killedHealth);
        killer.setHealthScale(killerHealth);
    }

    public void talking(Player killed, Player killer) {
        killed.sendRawMessage(ChatColor.RED + killer.getName() + "님이 체력을 약탈했습니다.");
        killer.sendRawMessage(ChatColor.RED + killed.getName() + "님의 체력을 약탈했습니다.");
    }
}
