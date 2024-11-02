package teamzesa.DataBase.entity.Enhance;

import org.bukkit.Material;

public interface Weapon extends EnhanceItemInterface {

    static <E extends Weapon> E findByMaterial(Material material) throws Exception {
        return null;
    }

    Material getMaterial();

    double getShortRangeDamage();
}
