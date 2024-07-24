package teamzesa.event.Enhance.Interface;

import org.bukkit.Material;

public interface Weapon extends EnhanceItemCache {

    static <E extends Weapon> E findByMaterial(Material material) throws Exception {
        return null;
    }

    Material getMaterial();

    double getShortRangeDamage();

    double getLongRangeDamage();
}
