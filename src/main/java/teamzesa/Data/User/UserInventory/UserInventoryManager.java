package teamzesa.Data.User.UserInventory;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserInventoryManager {

    private static class UserInventoryManagerHolder {
        private final static UserInventoryManager INSTANCE = new UserInventoryManager();
    }

    public static UserInventoryManager getUserInventoryManager() {
        return UserInventoryManagerHolder.INSTANCE;
    }

    private final Map<UUID, Inventory> userInventoryCachedData;

    public UserInventoryManager() {
        this.userInventoryCachedData = new HashMap<>();
    }

    public void insert(UUID uuid, Inventory inventory) {
        this.userInventoryCachedData.put(uuid, inventory);
    }

    public Inventory get(UUID uuid) {
        return this.userInventoryCachedData.get(uuid);
    }

    public void remove(UUID uuid) {
        this.userInventoryCachedData.remove(uuid);
    }
}
