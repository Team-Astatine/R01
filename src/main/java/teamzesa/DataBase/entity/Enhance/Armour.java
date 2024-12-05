package teamzesa.DataBase.entity.Enhance;

import org.bukkit.Material;

public interface Armour extends EnhanceItemInterface {
    static <E extends Armour> E findByMaterial(Material material) throws Exception {
        return null;
    }

    Material getMaterial();
}
