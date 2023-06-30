package teamzesa.combat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ExplosiveHandler implements Listener {
    @EventHandler
    public void explosive(ExplosionPrimeEvent e) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                e.setRadius(50);
                e.setFire(true);
            }
        };
        task.run();
    }

    @EventHandler
    public void creeperSet(CreeperPowerEvent e) {
    }
}
