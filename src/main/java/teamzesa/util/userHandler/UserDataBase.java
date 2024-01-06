package teamzesa.util.userHandler;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.ComponentExchanger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserDataBase extends ComponentExchanger {
    private static class UserMapHandlerHolder {
        private static final UserDataBase INSTANCE = new UserDataBase();
    }

    private final static ConcurrentHashMap<UUID, User> USER_DATA = new ConcurrentHashMap<>();

    public static UserDataBase getUserMapHandler() {
        return UserMapHandlerHolder.INSTANCE;
    }

    public synchronized ConcurrentHashMap<UUID,User> getAllUserTable() {
        return USER_DATA;
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
