package teamzesa.DataBase.UserInventorySave;

import teamzesa.DataBase.UserCachingData;
import teamzesa.DataBase.entity.AccessObject;
import teamzesa.DataBase.entity.UserInventory.UserInventory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserInventorySaveManager implements AccessObject<UUID, UserInventory> {

    private static final ConcurrentHashMap<UUID, UserInventory> INVENTORY_DATA = UserCachingData.getUserInventoryInstance();

    private static class DaoUserInventoryHolder {
        private final static UserInventorySaveManager INSTANCE = new UserInventorySaveManager();
    }

    public static UserInventorySaveManager getInstance() {
        return UserInventorySaveManager.DaoUserInventoryHolder.INSTANCE;
    }

    public synchronized ConcurrentHashMap<UUID, UserInventory> getAllUserTable() {
        return new ConcurrentHashMap<>(INVENTORY_DATA);
    }

    @Override
    public synchronized Boolean insert(UserInventory userInventory) {
        INVENTORY_DATA.put(userInventory.uuid(), userInventory);
        return Boolean.TRUE;
    }

    @Override
    public synchronized UserInventory select(UUID uuid) {
        return INVENTORY_DATA.get(uuid);
    }

    @Override
    public synchronized Boolean update(UserInventory userInventory) {
        INVENTORY_DATA.replace(userInventory.uuid(), userInventory);
        return Boolean.TRUE;
    }

    @Override
    public synchronized Boolean clear() {
        INVENTORY_DATA.clear();
        return Boolean.TRUE;
    }
}
