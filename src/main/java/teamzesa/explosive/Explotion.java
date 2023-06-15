package teamzesa.explosive;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Explotion implements Listener {
    @EventHandler
    public boolean explosive(ExplosionPrimeEvent e) {
        if (e.getEntity().getType() != EntityType.MINECART_TNT)
            return false;
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                e.setRadius(20);
                e.setFire(true);
            }
        };
        task.runTaskLater(Bukkit.getPluginManager().getPlugin("R01"), 0L);
        return true;
    }
}
