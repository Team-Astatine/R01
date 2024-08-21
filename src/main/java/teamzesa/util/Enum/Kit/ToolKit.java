package teamzesa.util.Enum.Kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ToolKit {
    IRON_AXE(new ItemStack(Material.IRON_AXE), 1),
    SHIELD(new ItemStack(Material.SHIELD), 1),
    IRON_INGOT(new ItemStack(Material.IRON_INGOT), 1);

    private final ItemStack toolKit;
    private final int itemCount;

    ToolKit(ItemStack itemStack, int itemCount) {
        this.toolKit = itemStack;
        this.itemCount = itemCount;
    }

    public ItemStack getToolKit() {
        return this.toolKit;
    }

    public int getItemCount() {
        return itemCount;
    }
}
