package teamzesa.expHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerExpHealthScale implements Listener {

    private final double MAX_HEALTH_SCALE = 60.0;
    private final int STANDARD_LEVEL = 10;
    private final int DEFAULT_PLAYER_HEALTH = 20;

    @EventHandler
    public void expChangeEvent(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        int playerLevel = p.getLevel();
        double playerHealthScale = p.getHealthScale();

        if (playerHealthScale > MAX_HEALTH_SCALE)
            return;

        if (playerLevel % STANDARD_LEVEL != 0)
            return;

        int extraHealth = (playerLevel / STANDARD_LEVEL) * 2;
        p.setHealthScale(
                DEFAULT_PLAYER_HEALTH + (double)extraHealth
        );
    }
}
