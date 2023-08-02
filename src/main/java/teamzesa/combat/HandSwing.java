package teamzesa.combat;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;


public class HandSwing implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onSwing(PlayerArmSwingEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND)
            e.setCancelled(true);

        Player player = (Player) e.getPlayer();
        player.swingOffHand();
    }
}
