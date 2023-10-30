package teamzesa.IOHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserMapHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserIOHandler implements IOHandler {
    private static class UserIOHandlerHolder {
        private static final UserIOHandler INSTANCE = new UserIOHandler();
    }
    private File file;
    private final UserMapHandler userMapHandler;

    private UserIOHandler() {
        userMapHandler = UserMapHandler.getUserMapHandler();
    }

    @Override
    public void fileLoader(File file) {
        this.file = file;
    }

    public static UserIOHandler getIOHandler() {
        return UserIOHandlerHolder.INSTANCE;
    }

    public void importUserData() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            userMapHandler.updateAllUserData(gson.fromJson(reader, User[].class));
        } catch (IOException e) {
            System.err.println("InputUserData Exception");
            e.printStackTrace();
        }
    }

    public void exportUserData() {
        List<User> userData = new ArrayList<>(userMapHandler.getUserMap().values());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(userData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
