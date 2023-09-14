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
            case PRIMED_TNT -> boomBer();
            case CREEPER -> creeperBoom();
            case FIREBALL -> ghastBoom();
            case WITHER_SKULL -> witherBoom();
            case MINECART_TNT -> cartBoom();
            default -> this.event.setCancelled(true);
        }
    }

    private void boomBer() {
        event.setCancelled(true);
        World world = event.getEntity().getWorld();
        world.createExplosion(event.getEntity().getLocation(), 20.0F, true);
    }

    private void creeperBoom() {
        Creeper creeper = (Creeper) event.getEntity();
        int explosiveRadius = 20;

        if (creeper.isPowered())
            explosiveRadius = 100;

        event.setRadius(explosiveRadius);
        event.setFire(true);
    }

    private void ghastBoom() {
        event.setRadius(10);
        event.setFire(true);
    }

    private void witherBoom() {
        WitherSkull witherSkull = (WitherSkull) event.getEntity();
        if (witherSkull.isCharged())
            event.setRadius(40);
        if (!witherSkull.isCharged())
            event.setRadius(100);
    }

    private void cartBoom() {
        Location location = event.getEntity().getLocation();
        BukkitRunnable explosiveTask = new BukkitRunnable() {
            @Override
            public void run() {
                location.createExplosion(40,true);
            }
        };

        for (int i = 0; i < 100; i++)
            threadPool.addTask(explosiveTask);
    }
}
