package teamzesa.Event.RandomTeleport;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

//@Deprecated
public class RandomPositionGenerator {
    private final int MAX_RANDOM_TP = 3000;
    private final int MIN_RANDOM_TP = -3000;

    private final int[] position = new int[3];
    private boolean allowedPosition = false;

    public int numGenerator() {
        int range = MAX_RANDOM_TP - MIN_RANDOM_TP + 1;
        return (int) (Math.random() * range) + MIN_RANDOM_TP;
    }

    public int[] getRandomPosition(World world) {
        boolean ground = false, legRoom = false, bodyRoom = false;

        int maxHigh = world.getMaxHeight() - 1; //320 > Material_VOID_AIR
        int minHigh = 62; // world.getMinHeight == -64 , ocean is 62

        int x = numGenerator();
        int z = numGenerator();

        Block block = null;
        for (int y = maxHigh; y >= minHigh; y--) {
            block = world.getBlockAt(x, y, z);
            if (ObjectUtils.notEqual(block.getType(), Material.AIR))
                ground = true;

            block = world.getBlockAt(x, y + 2, z);
            if (block.getType().equals(Material.AIR))
                bodyRoom = true;

            block = world.getBlockAt(x, y + 1, z);
            if (block.getType().equals(Material.AIR))
                legRoom = true;

            if (ground && bodyRoom && legRoom) {
                this.allowedPosition = true;
                this.position[0] = x;
                this.position[1] = y + 1;
                this.position[2] = z;
                break;
            }
        }

        if (this.allowedPosition)
            return this.position;

        return getRandomPosition(world);
    }
}
