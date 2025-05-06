package teamzesa.Event.Enhance.PlayerInteraction.UserInterface;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnhanceInventoryManager {

    private static class EnhanceInventoryManagerHolder {
        private final static EnhanceInventoryManager INSTANCE = new EnhanceInventoryManager();
    }

    public static EnhanceInventoryManager getEnhanceInventoryManager() {
        return EnhanceInventoryManagerHolder.INSTANCE;
    }

    private final Map<UUID, Inventory> enhanceInventoryCachedData;

    public EnhanceInventoryManager() {
        this.enhanceInventoryCachedData = new HashMap<>();
    }

    public void insert(UUID uuid, Inventory inventory) {
        this.enhanceInventoryCachedData.put(uuid, inventory);
    }

    public Inventory get(UUID uuid) {
        return this.enhanceInventoryCachedData.get(uuid);
    }

    public void remove(UUID uuid) {
        this.enhanceInventoryCachedData.remove(uuid);
    }
}
