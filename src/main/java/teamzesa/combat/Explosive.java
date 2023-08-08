package teamzesa.combat;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class Explosive implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void explosiveMineCart(ExplosionPrimeEvent e) {
        switch (e.getEntityType()) {
            case PRIMED_TNT -> boomBer(e);
            case CREEPER -> creeperBoom(e);
            case GHAST -> ghastBoom(e);
            case WITHER_SKULL -> witherBoom(e);
            case MINECART_TNT -> cartBoom(e);
            default -> e.setCancelled(true);
        }
    }

    private void boomBer(ExplosionPrimeEvent e) {
        e.setCancelled(true);
        e.getEntity()
                .getWorld()
                .createExplosion(e.getEntity().getLocation(), e.getRadius());
    }

    private void creeperBoom(ExplosionPrimeEvent e) {

    }

    private void ghastBoom(ExplosionPrimeEvent e) {

    }

    private void witherBoom(ExplosionPrimeEvent e) {

    }

    private void cartBoom(ExplosionPrimeEvent e) {

    }
}
