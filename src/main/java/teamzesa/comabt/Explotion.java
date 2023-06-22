package teamzesa.comabt;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Explotion implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void explosive(ExplosionPrimeEvent e) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                e.setRadius(10);
                e.setFire(true);
            }
        };
    }

    @EventHandler
    public void creeperSet(CreeperPowerEvent e) {
    }
}
