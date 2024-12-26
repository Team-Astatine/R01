package teamzesa.Enum.Kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum FoodKit {

    COOKED_BEEF(new ItemStack(Material.COOKED_BEEF, 20)),
    GOLDEN_APPLE(new ItemStack(Material.GOLDEN_APPLE, 2));

    private final ItemStack foodKit;

    FoodKit(ItemStack itemStack) {
        this.foodKit = itemStack;
    }

    public ItemStack getFood() {
        return this.foodKit;
    }
}
