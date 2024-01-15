package teamzesa.event;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.Bukkit;
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
    public void onSwing(@NotNull PlayerArmSwingEvent e) {
        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            return;
        }

        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) {
            return;
        }

        if (e.getAnimationType() == PlayerAnimationType.OFF_ARM_SWING)
            return;

        BukkitTask offHandSwing = Bukkit.getScheduler().runTaskLater(
                R01.getPlugin(R01.class),
                () -> e.getPlayer().swingOffHand(),
                7L
        );
    }
}
