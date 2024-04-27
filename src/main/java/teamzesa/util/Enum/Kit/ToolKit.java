package teamzesa.util.Enum.Kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ToolKit {
    NETHERITE_SWORD(new ItemStack(Material.NETHERITE_SWORD)),
    NETHERITE_PICKAXE(new ItemStack(Material.NETHERITE_PICKAXE)),
    NETHERITE_AXE(new ItemStack(Material.NETHERITE_AXE)),
    NETHERITE_SHOVEL(new ItemStack(Material.NETHERITE_SHOVEL));

    private final ItemStack toolKit;

    ToolKit(ItemStack itemStack) {
        this.toolKit = itemStack;
    }

    public ItemStack getToolKit() {
        return this.toolKit;
    }
}
