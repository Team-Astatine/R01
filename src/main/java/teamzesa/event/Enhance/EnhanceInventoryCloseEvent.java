package teamzesa.event.Enhance;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;

import java.util.*;

import static teamzesa.command.EnhanceDialog.EXECUTE_STUFF_DATA;

public class EnhanceInventoryCloseEvent implements EventRegister {
    private final int MINIMUM_INVENTORY_SLOT = 5;

    private Player itemOwner;
    private ArrayList<ItemStack> slot;

    private final InventoryCloseEvent event;
    public EnhanceInventoryCloseEvent(InventoryCloseEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.itemOwner = (Player) this.event.getPlayer();
        this.slot = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (validation())
            return;

        this.slot.add(this.event.getInventory().getItem(3));
        this.slot.add(this.event.getInventory().getItem(4));
        this.slot.add(this.event.getInventory().getItem(5));

        long receiveItemCount = this.slot.stream()
                .filter(Objects::nonNull)
                .count();

        long blankInventoryCount = Arrays.stream(this.itemOwner.getInventory().getContents())
                .filter(Objects::isNull)
                .count();

        if (blankInventoryCount >= MINIMUM_INVENTORY_SLOT + receiveItemCount)
            this.slot.stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> this.itemOwner.getInventory().addItem(item));
        else
            this.slot.stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> this.itemOwner.getWorld().dropItem(this.itemOwner.getLocation(), item));
    }

    private boolean validation() {
        if (this.event.getInventory().getType() != InventoryType.DROPPER)
            return true;

        ItemStack slotSevenItem = this.event.getView().getItem(7);
        if (slotSevenItem == null)
            return true;

        if (!slotSevenItem.hasItemMeta())
            return true;

        if (!slotSevenItem.getItemMeta().hasCustomModelData())
            return true;

        if (slotSevenItem.getItemMeta().getCustomModelData() != EXECUTE_STUFF_DATA)
            return true;
        return false;
    }
}