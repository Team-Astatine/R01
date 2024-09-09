package teamzesa.DataBase.entity.Enhance;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record EnhanceItem(
        Player enhancePlayer,
        ItemStack enhanceItem,
        ItemStack enhanceScroll,
        ItemStack protectScroll
) {
}
