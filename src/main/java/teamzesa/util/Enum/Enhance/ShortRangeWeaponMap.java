package teamzesa.util.Enum.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.Interface.EnhanceItemCache;
import teamzesa.event.Enhance.Interface.Weapon;
import teamzesa.exception.Enhance.EnhanceItemSearchException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ShortRangeWeaponMap implements Weapon, EnhanceItemCache {
    //    axe
    WOOD_AXE(Material.WOODEN_AXE, 7.0, 0.0),
    GOLD_AXE(Material.GOLDEN_AXE, 7.0, 0.0),
    STONE_AXE(Material.STONE_AXE, 9.0, 0.0),
    IRON_AXE(Material.IRON_AXE, 9.0, 0.0),
    DIAMOND_AXE(Material.DIAMOND_AXE, 9.0, 0.0),
    NETHER_AXE(Material.NETHERITE_AXE, 10.0, 0.0),

    //    sword
    WOOD_SWORD(Material.WOODEN_SWORD, 4.0, 0.0),
    GOLD_SWORD(Material.GOLDEN_SWORD, 4.0, 0.0),
    STONE_SWORD(Material.STONE_SWORD, 5.0, 0.0),
    IRON_SWORD(Material.IRON_SWORD, 6.0, 0.0),
    DIAMOND_SWORD(Material.DIAMOND_SWORD, 7.0, 0.0),
    NETHER_SWORD(Material.NETHERITE_SWORD, 8.0, 0.0),

    //    mace
    MACE(Material.MACE, 6.0, 0.0);

    private final Material material;
    private final double shortRangeDamage;
    private final double longRangeDamage;
    private static final Map<Material, ShortRangeWeaponMap> CACHED_ITEM = Arrays.stream(values())
            .collect(Collectors.toMap(item -> item.material, Function.identity()));

    ShortRangeWeaponMap(Material material, double shortRangeDamage, double longRangeDamage) {
        this.material = material;
        this.shortRangeDamage = shortRangeDamage;
        this.longRangeDamage = longRangeDamage;
    }

    public static ShortRangeWeaponMap findByItemStack(ItemStack itemStack) throws Exception {
        if (BooleanUtils.isFalse(CACHED_ITEM.containsKey(itemStack.getType())))
            throw new EnhanceItemSearchException("Non Register This Material");
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

    @Override
    public double getLongRangeDamage() {
        return longRangeDamage;
    }
}
