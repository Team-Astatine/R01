package teamzesa.DataBase.entity.UserInventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public record Item(
        Material material,
        Map<Enchantment, Integer> enchantments,
        int customModelData,
        int amount
) {
}
