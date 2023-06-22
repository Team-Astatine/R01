package teamzesa.combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerHealthScaleHandler implements Listener {
    private final double MAX_HEALTH_SCALE = 60.0;
    private final int STANDARD_LEVEL = 10;
    private final int DEFAULT_PLAYER_HEALTH = 20;

    @EventHandler
    public void expChangeEvent(PlayerLevelChangeEvent e) {
        Player player = e.getPlayer();
        updatePlayerHealthScale(player);
    }

    public void expChangeEvent(Player player) {
        updatePlayerHealthScale(player);
    }

    private void updatePlayerHealthScale(Player player) {
        int playerLevel = player.getLevel();
        double playerHealthScale = player.getHealthScale();

        if (playerHealthScale > MAX_HEALTH_SCALE)
            return;

        if (playerLevel < STANDARD_LEVEL)
            return;

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                int extraHealth = (playerLevel / STANDARD_LEVEL) * 2;
                player.setHealthScale(DEFAULT_PLAYER_HEALTH + (double) extraHealth);
            }
        };

        task.runTaskLater(Bukkit.getPluginManager().getPlugin("R01"), 1L);
    }
}
