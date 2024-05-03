package teamzesa.util.Enum.Kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ArmourKit {
    NETHERITE_HELMET(new ItemStack(Material.NETHERITE_HELMET)),
    NETHERITE_CHESTPLATE(new ItemStack(Material.NETHERITE_CHESTPLATE)),
    NETHERITE_LEGGINS(new ItemStack(Material.NETHERITE_LEGGINGS)),
    NETHERITE_BOOTS(new ItemStack(Material.NETHERITE_BOOTS));

    private final ItemStack returnStuff;

    ArmourKit(ItemStack itemStack) {
        this.returnStuff = itemStack;
    }

    public ItemStack getArmour() {
        return this.returnStuff;
    }
}
