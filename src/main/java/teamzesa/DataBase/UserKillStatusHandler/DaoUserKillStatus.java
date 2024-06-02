package teamzesa.DataBase.UserKillStatusHandler;

import teamzesa.DataBase.DAO;
import teamzesa.DataBase.DataBase;
import teamzesa.DataBase.entity.User;
import teamzesa.DataBase.entity.UserKillStatus;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DaoUserKillStatus implements DAO<UUID, UserKillStatus> {
    private static final ConcurrentHashMap<UUID, UserKillStatus> KILL_STATUS = DataBase.getUserKillStatusInstance();

    private static class DaoUserKillStatusHolder {
        private final static DaoUserKillStatus INSTANCE = new DaoUserKillStatus();
    }

    public static DaoUserKillStatus getInstance() {
        return DaoUserKillStatusHolder.INSTANCE;
    }

    public synchronized ConcurrentHashMap<UUID, UserKillStatus> getAllUserTable() {
        return new ConcurrentHashMap<>(KILL_STATUS);
    }

    @Override
    public synchronized Boolean insert(UserKillStatus userKillStatus) {
        KILL_STATUS.put(userKillStatus.uuid(), userKillStatus);
        return Boolean.TRUE;
    }

    @Override
    public synchronized UserKillStatus select(UUID uuid) {
        return KILL_STATUS.get(uuid);
    }

    @Override
    public synchronized Boolean update(UserKillStatus userKillStatus) {
        KILL_STATUS.replace(userKillStatus.uuid(), userKillStatus);
        return Boolean.TRUE;
    }

    @Override
    public synchronized Boolean clear() {
        KILL_STATUS.clear();
        return Boolean.TRUE;
    }
}
