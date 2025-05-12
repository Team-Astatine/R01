package teamzesa.Util.UserUIGenerator;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import teamzesa.Util.Function.StringComponentExchanger;
import teamzesa.Util.UserUIGenerator.UIGenerator.SlotItemMapping;

import java.util.List;

public class InventoryUIGenerator extends StringComponentExchanger {
    public Inventory enhanceDialog;
    public Player chestOwner;

    public InventoryUIGenerator() {}

    public InventoryUIGenerator chestOwner(Player chestOwner) {
        this.chestOwner = chestOwner;
        return this;
    }

    public InventoryUIGenerator setInventory(InventoryType inventoryType, Component component) {
        this.enhanceDialog = Bukkit.createInventory(this.chestOwner, inventoryType, component);
        return this;
    }

    public InventoryUIGenerator setInventory(int slotCount, Component component) {
        this.enhanceDialog = Bukkit.createInventory(this.chestOwner, slotCount, component);
        return this;
    }

    public InventoryUIGenerator setEnhanceUIItem(List<SlotItemMapping> slotItemMappings) {
        for (SlotItemMapping elements : slotItemMappings)
            this.enhanceDialog.setItem(elements.slot(), elements.itemStack());
        return this;
    }

    public Inventory executeUI() {
        this.chestOwner.openInventory(this.enhanceDialog);
        return this.enhanceDialog;
    }
}
