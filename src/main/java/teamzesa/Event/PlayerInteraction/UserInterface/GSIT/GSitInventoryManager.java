package teamzesa.Event.PlayerInteraction.UserInterface.GSIT;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GSitInventoryManager {

    private static class GSitInventoryManagerHolder {
        private final static GSitInventoryManager INSTANCE = new GSitInventoryManager();
    }

    public static GSitInventoryManager getGSitInventoryManager() {
        return GSitInventoryManagerHolder.INSTANCE;
    }

    private final Map<UUID, Inventory> gsitInventoryCachedData;

    public GSitInventoryManager() {
        this.gsitInventoryCachedData = new HashMap<>();
    }

    public synchronized void insert(UUID uuid, Inventory inventory) {
        this.gsitInventoryCachedData.put(uuid, inventory);
    }

    public synchronized Inventory get(UUID uuid) {
        return this.gsitInventoryCachedData.get(uuid);
    }

    public synchronized void remove(UUID uuid) {
        this.gsitInventoryCachedData.remove(uuid);
    }
}
