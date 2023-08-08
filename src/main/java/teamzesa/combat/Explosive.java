package teamzesa.combat;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class Explosive implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void explosiveMineCart(ExplosionPrimeEvent e) {
        switch (e.getEntityType()) {
            case PRIMED_TNT -> {boomBer();  break;}
            case CREEPER -> {creeperBoom(); break;}
            case GHAST -> {ghastBoom(); break;}
            case WITHER_SKULL -> {witherBoom(); break;}
            case MINECART_TNT -> {cartBoom();   break;}
            default -> e.setCancelled(true);
        }
    }

    private void boomBer() {

    }

    private void creeperBoom() {

    }

    private void ghastBoom() {

    }

    private void witherBoom() {

    }

    private void cartBoom() {

    }
}
