package teamzesa.util;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Arrays;

//@Deprecated
public class RanNumGenerator {
    private final int MAX_RANDOM_TP = 3000;
    private final int MIN_RANDOM_TP = -3000;

    private int[] position = new int[3];
    private boolean isAllowedPosition = false;

    public int numGenerator() {
        int range = MAX_RANDOM_TP - MIN_RANDOM_TP + 1;
        return (int) (Math.random() * range) + MIN_RANDOM_TP;
        }

    public int[] groundChecker(World world) {
//        System.out.println(this.isAllowedPosition);

        boolean isGround = false, hasLegRoom = false, hasBodyRoom = false;

        int maxHigh = world.getMaxHeight() - 1; //320 > Material_VOID_AIR
        int minHigh = 62; // world.getMinHeight == -64 , ocean is 62

        int x = numGenerator();
        int z = numGenerator();

        Block block = null;
        for (int y = maxHigh; y >= minHigh; y--) {
            block = world.getBlockAt(x, y, z);
            if (block.getType() != Material.AIR)
                isGround = true;

            block = world.getBlockAt(x,y + 2,z);
            if (block.getType() == Material.AIR)
                hasBodyRoom = true;

            block = world.getBlockAt(x,y + 1,z);
            if (block.getType() == Material.AIR)
                hasLegRoom = true;

            if (isGround && hasBodyRoom && hasLegRoom) {
                this.isAllowedPosition = true;
                this.position[0] = x;
                this.position[1] = y + 1;
                this.position[2] = z;
                break;
            }
        }

//        System.out.println(Arrays.toString(this.position));
//        System.out.println(world.getBlockAt(x,position[1] + 2,z).getType());
//        System.out.println(world.getBlockAt(x,position[1] + 1,z).getType());
//        System.out.println(world.getBlockAt(x,position[1],z).getType()+"\n\n");

        if (this.isAllowedPosition)
            return this.position;

        return groundChecker(world);
    }
}
