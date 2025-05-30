package teamzesa.Data.User.UserKillStatus;

import teamzesa.Data.User.Interface.AccessObject;
import teamzesa.Data.User.UserDataCacheHolder;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserKillStatusAccessManager implements AccessObject<UUID, UserKillStatus> {
    private static final ConcurrentHashMap<UUID, UserKillStatus> KILL_STATUS = UserDataCacheHolder.getUserKillStatusInstance();

    private static class DaoUserKillStatusHolder {
        private final static UserKillStatusAccessManager INSTANCE = new UserKillStatusAccessManager();
    }

    public static UserKillStatusAccessManager getInstance() {
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
