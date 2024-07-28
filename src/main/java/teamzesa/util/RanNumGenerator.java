package teamzesa.util;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

//@Deprecated
public class RanNumGenerator {
    private static final int MAX_RANDOM_TP = 3000;
    private static final int MIN_RANDOM_TP = -3000;

    public static int numGenerator() {
        int range = MAX_RANDOM_TP - MIN_RANDOM_TP + 1;
        return (int) (Math.random() * range) + MIN_RANDOM_TP;
        }

    public static int[] groundChecker(World world) {
        Block block = null;
        boolean hasLegRoom = false;
        boolean hasBodyRoom = false;
        boolean isGround = false;

        int maxHigh = world.getMaxHeight() - 1; //320 > Material_VOID_AIR
        int minHigh = 62; // world.getMinHeight == -64 , ocean is 62

        int x = numGenerator();
        int z = numGenerator();

        for (int y = maxHigh; y >= minHigh; y--) {
            block = world.getBlockAt(x, y, z);
            if (block.getType() != Material.AIR)
                isGround = true;

            block = world.getBlockAt(x,y + 1,z);
            if (block.getType() == Material.AIR || block.getType() == Material.WATER)
                hasLegRoom = true;

            block = world.getBlockAt(x,y + 2,z);
            if (block.getType() == Material.AIR || block.getType() == Material.WATER)
                hasBodyRoom = true;

            if (isGround && hasBodyRoom && hasLegRoom)
                return new int[]{x, y, z};

        }
        return null;
    }
}
