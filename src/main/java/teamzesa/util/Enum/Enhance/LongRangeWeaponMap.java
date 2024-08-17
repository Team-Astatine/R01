package teamzesa.util.Enum.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.Interface.EnhanceItemCache;
import teamzesa.event.Enhance.Interface.Weapon;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LongRangeWeaponMap implements Weapon, EnhanceItemCache {
    AIR(Material.AIR, 0, 0),

    TRIDENT(Material.TRIDENT, 9.0, 8.0),
    CROSSBOW(Material.CROSSBOW, 0.0, 7.0),
    BOW(Material.BOW, 0.0, 6.0);

    private final Material material;
    private final double shortRangeDamage;
    private final double longRangeDamage;
    private static final Map<Material, LongRangeWeaponMap> CACHED_ITEM = Arrays.stream(values())
            .collect(Collectors.toMap(item -> item.material, Function.identity()));

    LongRangeWeaponMap(Material material, double shortRangeDamage, double longRangeDamage) {
        this.material = material;
        this.shortRangeDamage = shortRangeDamage;
        this.longRangeDamage = longRangeDamage;
    }

    public static LongRangeWeaponMap findByItemStack(ItemStack itemStack) {
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

    @Override
    public double getLongRangeDamage() {
        return longRangeDamage;
    }
}
