package teamzesa.DataBase;

import teamzesa.entity.User;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase extends StringComponentExchanger {
    private static class UserDataHolder {
        private final static ConcurrentHashMap<UUID, User> INSTANCE = new ConcurrentHashMap<>();
    }

    private static ConcurrentHashMap<UUID,User> getUserData() {
        return UserDataHolder.INSTANCE;
    }


}
