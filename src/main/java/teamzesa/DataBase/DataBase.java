package teamzesa.DataBase;

import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
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
}
