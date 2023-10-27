package teamzesa.userEvent;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.ComponentExchanger;

import java.awt.*;


public class Respawn extends ComponentExchanger implements Listener {
    private PlayerRespawnEvent event;

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        this.event = e;
        deathRandomTeleport();
    }

    public void deathRandomTeleport() {
        Player player = this.event.getPlayer();

        if (player.getBedSpawnLocation() != null) {
            playerAnnouncer(player, "침대로 텔레포트 됩니다.", Color.YELLOW);
            return;
        }

        int MIN_RANDOM_TP = -1000;
        int MAX_RANDOM_TP = 1000;

        double x = ranNumGenerator(MAX_RANDOM_TP, MIN_RANDOM_TP);
        double z = ranNumGenerator(MAX_RANDOM_TP, MIN_RANDOM_TP);
        double y = groundChecker(player.getWorld(),x,z);

        this.event.setRespawnLocation(new Location(player.getWorld(),x,y,z));
        playerAnnouncer(player,"침대가 없어 랜덤 텔레포트 되었습니다.", Color.ORANGE);
    }

    private double ranNumGenerator(int maxValue, int minValue) {
        int range = maxValue - minValue + 1;
        return (Math.random() * range) + minValue;
    }

    private double groundChecker(World world, double x, double z) {
        int maxHigh = world.getMaxHeight() - 1; //320 > Material_VOID_AIR
        int minHigh = world.getMinHeight(); //-64

        for (int i = maxHigh; i > minHigh; i--) {
            Block block = world.getBlockAt((int)x,i,(int)z);
            if (block.getType() != Material.AIR)
                return i;
        }
        return 0;
    }
}
