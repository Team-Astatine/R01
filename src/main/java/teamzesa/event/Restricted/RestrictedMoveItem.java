package teamzesa.event.Restricted;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import teamzesa.event.EventRegister.EventRegister;

import java.util.HashSet;

public class RestrictedMoveItem implements EventRegister {
    private final InventoryMoveItemEvent event;
    private HashSet<Material> banItem;

    public RestrictedMoveItem(InventoryMoveItemEvent event) {
        this.event = event;
        this.banItem = new HashSet<>();
        init();
        execute();
    }

    @Override
    public void init() {
        this.banItem.add(Material.ARMOR_STAND);
    }

    @Override
    public void execute() {
        if (this.event.getDestination().getType() != InventoryType.DISPENSER)
            return;

        boolean isBannedItem = this.banItem.stream()
                .anyMatch(item -> item.equals(this.event.getItem().getType()));

        if (!isBannedItem)
            return;

        this.event.setCancelled(true);
    }
}
