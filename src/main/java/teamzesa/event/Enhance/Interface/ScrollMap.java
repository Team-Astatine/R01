package teamzesa.event.Enhance.Interface;

import org.bukkit.Material;

public interface ScrollMap extends EnhanceItemCache {

    static <E extends ScrollMap> E findByMaterial(Material material) throws Exception {
        return null;
    }

    Material getMaterial();

    int getDiscountValue();
}
