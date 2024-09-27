package teamzesa.DataBase.enhance;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface Scroll extends EnhanceItem {

    static <E extends Scroll> E findByMaterial(ItemStack material) throws Exception {
        return null;
    }

    Material getMaterial();

    int getDiscountValue();
}
