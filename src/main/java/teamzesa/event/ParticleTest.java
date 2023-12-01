package teamzesa.event;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleTest extends BukkitRunnable {

    private Player player;

    public ParticleTest(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        Location playerLocation = player.getLocation();
        Particle.DustOptions particle = new Particle.DustOptions(Color.fromRGB(255,182,193),1);
        playerLocation.getWorld().spawnParticle(Particle.CHERRY_LEAVES, playerLocation, 1, 0, -1, 0, 0, particle);
    }
}
