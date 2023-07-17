package teamzesa.combat;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class HandSwing implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerHandSwing(PlayerArmSwingEvent e) {
        Player player = e.getPlayer();
        player.swingMainHand();
        player.swingOffHand();
    }
}
