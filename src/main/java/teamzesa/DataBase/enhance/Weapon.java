package teamzesa.DataBase.enhance;

import org.bukkit.Material;

public interface Weapon extends EnhanceItem {

    static <E extends Weapon> E findByMaterial(Material material) throws Exception {
        return null;
    }

    Material getMaterial();

    double getShortRangeDamage();

    double getLongRangeDamage();
}
