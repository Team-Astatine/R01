package teamzesa.DataBase;

import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.entity.UserInventory.UserInventory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserCachingData {
    private static class UserDataHolder {
        private final static ConcurrentHashMap<UUID, User> INSTANCE = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<UUID, User> getUserDataInstance() {
        return UserDataHolder.INSTANCE;
    }


    private static class UserKillStatusHolder {
        private final static ConcurrentHashMap<UUID, UserKillStatus> INSTANCE = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<UUID, UserKillStatus> getUserKillStatusInstance() {
        return UserKillStatusHolder.INSTANCE;
    }


    public static class UserInventoryHolder {
        private final static ConcurrentHashMap<UUID, UserInventory> INSTANCE = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<UUID, UserInventory> getUserInventoryInstance() {
        return UserInventoryHolder.INSTANCE;
    }
}
