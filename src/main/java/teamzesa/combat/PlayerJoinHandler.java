package teamzesa.combat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {
    private PlayerHealthScaleHandler playerExpHealthScale;

    public PlayerJoinHandler() {
        playerExpHealthScale = new PlayerHealthScaleHandler();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        playerExpHealthScale.expChangeEvent(player);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }
}
