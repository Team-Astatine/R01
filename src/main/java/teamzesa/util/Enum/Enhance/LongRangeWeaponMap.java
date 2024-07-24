package teamzesa.util.Enum.Enhance;

import org.bukkit.Material;
import teamzesa.event.Enhance.Interface.EnhanceItemCache;
import teamzesa.event.Enhance.Interface.Weapon;
import teamzesa.exception.Enhance.EnhanceItemSearchException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LongRangeWeaponMap implements Weapon, EnhanceItemCache {
    TRIDENT(Material.TRIDENT, 9.0, 8.0),
    CROSSBOW(Material.CROSSBOW, 0.0, 7.0),
    BOW(Material.BOW, 0.0, 6.0);

    private final Material material;
    private final double shortRangeDamage;
    private final double longRangeDamage;
    private static Map<Material, LongRangeWeaponMap> CACHED_ITEM = Arrays.stream(values())
            .collect(Collectors.toMap(item -> item.material, Function.identity()));

    LongRangeWeaponMap(Material material, double shortRangeDamage, double longRangeDamage) {
        this.material = material;
        this.shortRangeDamage = shortRangeDamage;
        this.longRangeDamage = longRangeDamage;
    }

    public static LongRangeWeaponMap findByMaterial(Material material) throws Exception {
        if (!CACHED_ITEM.containsKey(material))
            throw new EnhanceItemSearchException("Non Register This Material");
        return CACHED_ITEM.get(material);
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
