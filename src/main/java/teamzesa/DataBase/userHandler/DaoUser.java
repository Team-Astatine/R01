package teamzesa.DataBase.userHandler;

import teamzesa.DataBase.DAO;
import teamzesa.DataBase.DataBase;
import teamzesa.DataBase.entity.RObject.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DaoUser implements DAO<UUID, User> {

    private static final ConcurrentHashMap<UUID, User> USER_DATA = DataBase.getUserDataInstance();

    private static class DaoUserHolder {
        private final static DaoUser INSTANCE = new DaoUser();
    }

    public static DaoUser getInstance() {
        return DaoUserHolder.INSTANCE;
    }


    public synchronized ConcurrentHashMap<UUID, User> getAllUserTable() {
        return new ConcurrentHashMap<>(USER_DATA);
    }

    @Override
    public synchronized Boolean insert(User user) {
        USER_DATA.put(user.uuid(), user);
        return Boolean.TRUE;
    }

    @Override
    public synchronized User select(UUID uuid) {
        return USER_DATA.get(uuid);
    }

    @Override
    public synchronized Boolean update(User user) {
        USER_DATA.replace(user.uuid(), user);
        return Boolean.TRUE;
    }

    @Override
    public synchronized Boolean clear() {
        USER_DATA.clear();
        return Boolean.TRUE;
    }
}
