package teamzesa.combat;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;


public class Explosive implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void explosiveMineCart(ExplosionPrimeEvent e) {
        e.setRadius(100);
        e.setFire(true);
    }

    @EventHandler
    public void creeperSet(CreeperPowerEvent e) {}
}
