package teamzesa.combat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Explosive implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void explosive(ExplosionPrimeEvent e) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                e.setRadius(100);
                e.setFire(true);
            }
        };
        task.run();
    }

    @EventHandler
    public void creeperSet(CreeperPowerEvent e) {
    }
}
