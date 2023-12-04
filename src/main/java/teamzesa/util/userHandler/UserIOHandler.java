package teamzesa.util.userHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import teamzesa.entity.User;
import teamzesa.util.Enum.DataFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserIOHandler {
    private static class UserIOHandlerHolder {
        private static final UserIOHandler INSTANCE = new UserIOHandler();
    }
    private final UserMapHandler userMapHandler;

    private UserIOHandler() {
        userMapHandler = UserMapHandler.getUserMapHandler();
    }

    public static UserIOHandler getIOHandler() {
        return UserIOHandlerHolder.INSTANCE;
    }

    public void importUserData() {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(DataFile.USER_DATA.getFileInstance())) {
            User[] users = objectMapper.readValue(reader, User[].class);
            userMapHandler.updateAllUserData(users);
        } catch (IOException e) {
            System.err.println("InputUserData err");
            e.printStackTrace();
        }
    }

    public void exportUserData() {
        List<User> userData = new ArrayList<>(userMapHandler.getUserMap().values());
        for (User user : userData) {
            System.out.println(user);
        }
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(DataFile.USER_DATA.getFileInstance())) {
            gson.toJson(userData, writer);
        } catch (IOException e) {
            System.err.println("OutPutUserData err");
            e.printStackTrace();
        }
    }
}
