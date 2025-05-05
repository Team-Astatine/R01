package teamzesa.Enumeration.Enhance.Items.Weapon;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.Data.Function.Enhance.EnhanceItemInterface;
import teamzesa.Data.Function.Enhance.Weapon;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LongRange implements Weapon, EnhanceItemInterface {
    AIR(Material.AIR, 0, 0),

    TRIDENT(Material.TRIDENT, 9.0, 8.0),
    CROSSBOW(Material.CROSSBOW, 0.0, 7.0),
    BOW(Material.BOW, 0.0, 6.0);

    private final Material material;
    private final double shortRangeDamage;
    private final double longRangeDamage;
    private static final EnumMap<Material, LongRange> CACHED_ITEM =
            Arrays.stream(values()).collect(
                    Collectors.toMap(
                        LongRange::getMaterial,
                        Function.identity(),
                        (k1, k2) -> k1,
                        () -> new EnumMap<>(Material.class)
                    )
            );

    LongRange(Material material, double shortRangeDamage, double longRangeDamage) {
        this.material = material;
        this.shortRangeDamage = shortRangeDamage;
        this.longRangeDamage = longRangeDamage;
    }

    public static LongRange findByItemStack(ItemStack itemStack) {
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

    public double getLongRangeDamage() {
        return longRangeDamage;
    }
}
