package teamzesa.DataBase.entity.UserInventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public record Armour(
        Material material,
        Map<Enchantment, Integer> enchantments,
        int customModelData
) {
}
