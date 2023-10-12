package teamzesa.userEvent;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import teamzesa.ComponentExchanger;

public class RandomTeleport extends ComponentExchanger implements Listener {

    @EventHandler
    public void randomTeleport(BlockPlaceEvent e) {
        int x = ranNumGenerator(1000,-1000);
        int z = ranNumGenerator(1000,-1000);
        int y = groundChecker(e.getPlayer().getWorld(),x,z);

        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }

    public int ranNumGenerator(int maxValue, int minValue) {
        int range = maxValue - minValue + 1;
        return (int) (Math.random() * range) + minValue;
    }

    public int groundChecker(World world, int x, int z) {
        int maxHigh = world.getMaxHeight();

        System.out.println("maxHigh > " + maxHigh);
        for (int i = maxHigh; i >= 63; i--) {
            Material airType = world.getBlockAt(x,i,z).getBlockData().getMaterial();
//            System.out.println(airType);
            if (airType != Material.VOID_AIR && airType != Material.AIR) {
                System.out.println(airType);
                return i;
            }
        }
        return 0;
    }
}
