package teamzesa.IOHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import teamzesa.userValue.User;
import teamzesa.userValue.UserHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserIOHandler {
    private static class UserIOHandlerHolder {
        private static UserIOHandler INSTANCE = new UserIOHandler();
    }
    private final UserHandler userHandler;

    private UserIOHandler() {
        userHandler = UserHandler.getUserHandler();
    }

    public static UserIOHandler getIOHandler() {
        return UserIOHandlerHolder.INSTANCE;
    }

    public void inputUserData(File userDataFile) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(userDataFile)) {
            userHandler.updateAllUserData(gson.fromJson(reader, User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputUserData(File userDataFile) {
        List<User> userData = new ArrayList<>(userHandler.getUserMap().values());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(userDataFile)) {
            gson.toJson(userData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
