package teamzesa.DataBase.UserHandler.MenuGenerator;

import org.bukkit.inventory.ItemStack;

public record SlotItemMapping (
        int slot,
        ItemStack itemStack
){};