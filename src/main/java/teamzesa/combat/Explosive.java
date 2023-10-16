package teamzesa.combat;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.ThreadPool;

public class Explosive implements Listener {
    private ExplosionPrimeEvent event;
    private ThreadPool threadPool;
    public Explosive() {
        threadPool = ThreadPool.getThreadPool();
    }
    @EventHandler(priority = EventPriority.LOW)
    public void explosiveHandler(ExplosionPrimeEvent event) {
        this.event = event;
//        TNT: 4 blocks
//        Creeper:
//        Normal: 3 blocks
//        Charged: 6 blocks
//        Ghast Fireball: 1 block
//        Wither Skull:
//        Blue Skull: 1 block
//        Black Skull: Varies depending on difficulty
        switch (this.event.getEntityType()) {
            case CREEPER -> creeperBoom();
            case FIREBALL -> ghastBoom();
            case WITHER_SKULL -> witherBoom();
            case PRIMED_TNT -> boomBer();
            case MINECART_TNT -> cartBoom();
            default -> this.event.setCancelled(true);
        }
    }

    private void boomBer() {
        this.event.setRadius(30);
        this.event.setFire(true);
    }

    private void creeperBoom() {
        Creeper creeper = (Creeper) this.event.getEntity();
        int explosiveRadius = creeper.isPowered() ? 20 : 100;
        this.event.setRadius(explosiveRadius);
        this.event.setFire(true);
    }

    private void ghastBoom() {
        this.event.setRadius(10);
        this.event.setFire(true);
    }

    private void witherBoom() {
        WitherSkull witherSkull = (WitherSkull) this.event.getEntity();
        int explosiveRadius = witherSkull.isCharged() ? 40 : 100;
        this.event.setRadius(explosiveRadius);
    }

    private void cartBoom() {
        this.event.setCancelled(true);
        Location location = this.event.getEntity().getLocation();
        BukkitRunnable explosiveTask = new BukkitRunnable() {
            @Override
            public void run() {
                location.createExplosion(40,true);
            }
        };
        for (int i = 0; i < 50; i++)
            explosiveTask.run();
    }
}
