package teamzesa.DataBase.enhance;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnhanceInventoryHandler {

    private static class EnhanceInventoryHandlerHolder {
        private final static EnhanceInventoryHandler INSTANCE = new EnhanceInventoryHandler();
    }

    public static EnhanceInventoryHandler getEnhanceInventoryHandler() {
        return EnhanceInventoryHandlerHolder.INSTANCE;
    }

    private final Map<UUID, Inventory> enhanceInventoryCachedData;

    public final static int PANEL_STUFF_CUSTOM_DATA = 20000;
    public final static int EXECUTE_STUFF_DATA = 30000;
    public final static int EXECUTE_DISCORD_DATA = 40000;
    public final static int EXECUTE_NOTION_DATA = 50000;

    public EnhanceInventoryHandler() {
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
