package teamzesa.event.Enhance.Interface;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface ScrollMap extends EnhanceItemCache {

    static <E extends ScrollMap> E findByMaterial(ItemStack material) throws Exception {
        return null;
    }

    Material getMaterial();

    int getDiscountValue();
}
