package teamzesa.event;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import teamzesa.R01;


public class HandSwing implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public synchronized void onSwing(@NotNull PlayerArmSwingEvent e) {
        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
//            playerAnnouncer(player,"mainHandReturn");
            return;
        }

        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) {
//            playerAnnouncer(player, "offHandReturn");
            return;
        }

        if (e.getAnimationType() == PlayerAnimationType.OFF_ARM_SWING)
            return;

        BukkitTask offHandSwingTask = new BukkitRunnable() {
            @Override
            public void run() {
//                playerAnnouncer(player,"Start Off Hand Swing");
                e.getPlayer().swingOffHand();
            }
        }.runTaskLater(R01.getPlugin(R01.class), 7L);
    }
}
