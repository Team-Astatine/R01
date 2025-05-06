package teamzesa.Event.PlayerInteraction.UserInterface;

import org.bukkit.inventory.ItemStack;

public record SlotItemMapping (
        int slot,
        ItemStack itemStack
){};