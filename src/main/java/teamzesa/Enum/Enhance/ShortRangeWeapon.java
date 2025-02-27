package teamzesa.Enum.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.entity.Enhance.Weapon;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ShortRangeWeapon implements Weapon {
    AIR(Material.AIR, 0, 0),

    //    axe
    WOOD_AXE(Material.WOODEN_AXE, 7.0, 0.0),
    GOLD_AXE(Material.GOLDEN_AXE, 7.0, 0.0),
    STONE_AXE(Material.STONE_AXE, 9.0, 0.0),
    IRON_AXE(Material.IRON_AXE, 9.0, 0.0),
    DIAMOND_AXE(Material.DIAMOND_AXE, 9.0, 0.0),
    NETHERITE_AXE(Material.NETHERITE_AXE, 10.0, 0.0),

    //    sword
    WOOD_SWORD(Material.WOODEN_SWORD, 4.0, 0.0),
    GOLD_SWORD(Material.GOLDEN_SWORD, 4.0, 0.0),
    STONE_SWORD(Material.STONE_SWORD, 5.0, 0.0),
    IRON_SWORD(Material.IRON_SWORD, 6.0, 0.0),
    DIAMOND_SWORD(Material.DIAMOND_SWORD, 7.0, 0.0),
    NETHERITE_SWORD(Material.NETHERITE_SWORD, 8.0, 0.0),

    //    mace
    MACE(Material.MACE, 6.0, 0.0);

    private final Material material;
    private final double shortRangeDamage;
    private static final Map<Material, ShortRangeWeapon> CACHED_ITEM =
            Arrays.stream(values()).collect(
                    Collectors.toMap(
                            ShortRangeWeapon::getMaterial,
                            Function.identity(),
                            (k1, k2) -> k1,
                            () -> new EnumMap<>(Material.class)
                    )
            );

    ShortRangeWeapon(Material material, double shortRangeDamage, double longRangeDamage) {
        this.material = material;
        this.shortRangeDamage = shortRangeDamage;
    }

    public static ShortRangeWeapon findByItemStack(ItemStack itemStack) {
        if (BooleanUtils.isFalse(CACHED_ITEM.containsKey(itemStack.getType())))
            return AIR;
        return CACHED_ITEM.get(itemStack.getType());
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public double getShortRangeDamage() {
        return shortRangeDamage;
    }
}
