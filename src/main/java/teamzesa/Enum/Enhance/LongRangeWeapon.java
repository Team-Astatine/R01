package teamzesa.Enum.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.entity.Enhance.EnhanceItemInterface;
import teamzesa.DataBase.entity.Enhance.Weapon;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LongRangeWeapon implements Weapon, EnhanceItemInterface {
    AIR(Material.AIR, 0, 0),

    TRIDENT(Material.TRIDENT, 9.0, 8.0),
    CROSSBOW(Material.CROSSBOW, 0.0, 7.0),
    BOW(Material.BOW, 0.0, 6.0);

    private final Material material;
    private final double shortRangeDamage;
    private final double longRangeDamage;
    private static final EnumMap<Material, LongRangeWeapon> CACHED_ITEM =
            Arrays.stream(values()).collect(
                    Collectors.toMap(
                        LongRangeWeapon::getMaterial,
                        Function.identity(),
                        (k1, k2) -> k1,
                        () -> new EnumMap<>(Material.class)
                    )
            );

    LongRangeWeapon(Material material, double shortRangeDamage, double longRangeDamage) {
        this.material = material;
        this.shortRangeDamage = shortRangeDamage;
        this.longRangeDamage = longRangeDamage;
    }

    public static LongRangeWeapon findByItemStack(ItemStack itemStack) {
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
