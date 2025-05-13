package teamzesa.Event.UserInterface.Function.UIGenerator;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import teamzesa.Util.Function.StringComponentExchanger;
import teamzesa.Event.UserInterface.Function.Interface.UIHolder;

import java.util.List;

public class InventoryUIGenerator extends StringComponentExchanger {
    private Player chestOwner;
    private UIHolder holder;

    private Inventory inventory;

    public InventoryUIGenerator() {}

    public InventoryUIGenerator bindHolder(UIHolder holder) {
        this.chestOwner = holder.getOwner();
        this.holder = holder;
        return this;
    }

    public InventoryUIGenerator inventoryGenerator(InventoryType inventoryType, Component component) {
        this.inventory = Bukkit.createInventory(this.holder, inventoryType, component);
        return this;
    }

    public InventoryUIGenerator inventoryGenerator(int slotCount, Component component) {
        this.inventory = Bukkit.createInventory(this.holder, slotCount, component);
        return this;
    }

    public InventoryUIGenerator setEnhanceUIItem(List<SlotItemMapping> slotItemMappings) {
        for (SlotItemMapping elements : slotItemMappings)
            this.inventory.setItem(elements.slot(), elements.itemStack());
        return this;
    }

    public Inventory executeUI() {
        this.chestOwner.openInventory(this.inventory);
        return this.inventory;
    }
}
