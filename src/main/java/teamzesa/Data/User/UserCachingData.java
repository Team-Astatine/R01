package teamzesa.Data.User;

import teamzesa.Data.User.UserData.User;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;

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
}
