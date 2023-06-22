package teamzesa.combat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinSet implements Listener {
    private PlayerExpHealthScale playerExpHealthScale;

    public PlayerJoinSet() {
        playerExpHealthScale = new PlayerExpHealthScale();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        playerExpHealthScale.expChangeEvent(player);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }
}
