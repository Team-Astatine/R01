package teamzesa.IOHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.userValue.User;
import teamzesa.userValue.UserHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserIOHandler implements IOHandler {
    private static class UserIOHandlerHolder {
        private static UserIOHandler INSTANCE = new UserIOHandler();
    }
    private File file;
    private final UserHandler userHandler;

    private UserIOHandler() {
        userHandler = UserHandler.getUserHandler();
    }

    @Override
    public void fileLoader(File file) {
        this.file = file;
    }

    public static UserIOHandler getIOHandler() {
        return UserIOHandlerHolder.INSTANCE;
    }

    public void inputUserData() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            userHandler.updateAllUserData(gson.fromJson(reader, User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputUserData() {
        List<User> userData = new ArrayList<>(userHandler.getUserMap().values());
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
