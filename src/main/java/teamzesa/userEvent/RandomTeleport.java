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
    private Player player;

    @EventHandler
    public void randomTeleport(BlockPlaceEvent e) {
        this.player = e.getPlayer();

        int x = ranNumGenerator(1000,-1000);
        int z = ranNumGenerator(1000,-1000);
        int y = groundChecker(e.getPlayer().getWorld(),x,z);

        System.out.println("x > " + x);
        System.out.println("y > " + y);
        System.out.println("z > " + z);
    }

    public int ranNumGenerator(int maxValue, int minValue) {
        int range = maxValue - minValue + 1;
        return (int) (Math.random() * range) + minValue;
    }

    public int groundChecker(World world, int x, int z) {
        int maxHigh = world.getMaxHeight() - 1; //320 > Material_VOID_AIR
        int minHigh = world.getMinHeight(); //-64

        for (int i = maxHigh; i > minHigh; i--) {
            Block block = world.getBlockAt(x,i,z);
//            System.out.println(block.getType());
                playerAnnouncer(this.player,i + " " + block.getType());
                System.out.println(i + " " + block.getType());
            if (block.getType() != Material.AIR) {
                String print = String.format("x > %d , y > %d , z > %d",x,i,z);
                playerAnnouncer(this.player,print);
                return i;
            }
        }
        return 0;
    }
}
