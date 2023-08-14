package teamzesa.combat;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import teamzesa.ComponentExchanger;
import teamzesa.R01;


public class HandSwing extends ComponentExchanger implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onSwing(PlayerArmSwingEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND)
            return;

        BukkitTask offHandSwingTask = new BukkitRunnable() {
            @Override
            public void run() {
                e.getPlayer().swingOffHand();
            }
        }.runTaskLater(R01.getPlugin(R01.class), 7L);
    }
}
