package teamzesa.util.Enum.Kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ToolKit {
    IRON_AXE(new ItemStack(Material.IRON_AXE), 1),
    SHIELD(new ItemStack(Material.SHIELD), 1),
    IRON_INGOT(new ItemStack(Material.IRON_INGOT), 16);

    private final ItemStack toolKit;
    private final int supplyAmount;

    ToolKit(ItemStack itemStack, int supplyAmount) {
        this.toolKit = itemStack;
        this.supplyAmount = supplyAmount;
    }

    public ItemStack getToolKit() {
        return this.toolKit;
    }

    public int getSupplyAmount() {
        return supplyAmount;
    }
}
