package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class Explosive implements Listener {
    private EntityExplodeEvent event;
    private Location location;

    @EventHandler(priority = EventPriority.LOW)
    public synchronized void explosiveHandler(EntityExplodeEvent event) {
        this.event = event;
        this.location = this.event.getLocation();

//        TNT: 4 blocks
//        Creeper:
//        Normal: 3 blocks
//        Charged: 6 blocks
//        Ghast Fireball: 1 block
//        Wither Skull:
//        Blue Skull: 1 block
//        Black Skull: Varies depending on difficulty
        this.event.setCancelled(true);
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
        this.location.createExplosion(25,true);
    }

    private void creeperBoom() {
        Creeper creeper = (Creeper) this.event.getEntity();
        int explosiveRadius = creeper.isPowered() ? 100 : 10;
        this.location.createExplosion(explosiveRadius,true);
    }

    private void ghastBoom() {
        this.location.createExplosion(20,true);
    }

    private void witherBoom() {
        WitherSkull witherSkull = (WitherSkull) this.event.getEntity();
        int explosiveRadius = witherSkull.isCharged() ? 130 : 40;
        this.location.createExplosion(explosiveRadius,true);
    }

    private void cartBoom() {
        Runnable tntCartTask = () -> {
//            this.event.blockList().stream()
//                    .filter(block -> block.getType().equals(Material.OBSIDIAN))
//                    .forEach(block -> block.setType(Material.AIR));
            this.location.createExplosion(200,true);
        };
        tntCartTask.run();
//        ThreadPool.getThreadPool().addTask(tntCartTask);
    }
}
