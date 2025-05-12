package teamzesa.Util.UserUIGenerator.UIGenerator;

import org.bukkit.inventory.ItemStack;

public record SlotItemMapping (
        int slot,
        ItemStack itemStack
){};