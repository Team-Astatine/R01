package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Explosive implements Listener {
    private ExplosionPrimeEvent event;
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
        this.event.setRadius(25);
        this.event.setFire(true);
    }

    private void creeperBoom() {
        Creeper creeper = (Creeper) this.event.getEntity();
        int explosiveRadius = creeper.isPowered() ? 100 : 20;
        this.event.setRadius(explosiveRadius);
        this.event.setFire(true);
    }

    private void ghastBoom() {
        this.event.setRadius(10);
        this.event.setFire(true);
    }

    private void witherBoom() {
        WitherSkull witherSkull = (WitherSkull) this.event.getEntity();
        int explosiveRadius = witherSkull.isCharged() ? 100 : 40;
        this.event.setRadius(explosiveRadius);
    }

    private void cartBoom() {
        BukkitRunnable tntCartTask = new BukkitRunnable() {
            @Override
            public void run() {
                Location location = Explosive.this.event.getEntity().getLocation();
                location.createExplosion(200,true);
            }
        };
        tntCartTask.run();
//        ThreadPool.getThreadPool().addTask(tntCartTask);
    }
}
