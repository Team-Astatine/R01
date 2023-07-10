package teamzesa.combat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;


public class Explosive implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void explosiveMineCart(ExplosionPrimeEvent e) {
        e.setRadius(100);
        e.setFire(true);
    }

    @EventHandler
    public void creeperSet(CreeperPowerEvent e) {

    }
}
