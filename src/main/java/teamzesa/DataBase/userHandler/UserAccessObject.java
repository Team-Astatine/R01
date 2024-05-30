package teamzesa.DataBase.userHandler;

import teamzesa.DataBase.entity.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserAccessObject {

    private static final ConcurrentHashMap<UUID,User> USER_DATA = new ConcurrentHashMap<>();

    private static class UaoHolder {
        private final static UserAccessObject INSTANCE = new UserAccessObject();
    }

    public static UserAccessObject getInstance() {
        return UaoHolder.INSTANCE;
    }

    public synchronized ConcurrentHashMap<UUID,User> getAllUserTable() {
        return new ConcurrentHashMap<>(USER_DATA);
    }

    public synchronized boolean insert(User user) {
        USER_DATA.put(user.uuid(), user);
        return Boolean.TRUE;
    }

    public synchronized User select(UUID uuid) {
        return USER_DATA.get(uuid);
    }

    public synchronized Boolean update(User user) {
        USER_DATA.replace(user.uuid(), user);
        return Boolean.TRUE;
    }

    public synchronized Boolean clear() {
        USER_DATA.clear();
        return Boolean.TRUE;
    }
}
