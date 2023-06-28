package teamzesa.userValue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {
    private static IOHandler instance = new IOHandler();
    private final UserHandler userHandler;

    private IOHandler() {
        userHandler = UserHandler.getUserInstance();
    }

    public static IOHandler getIOInstance() {
        return instance;
    }

    public void inputUserData(File userDataFile) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(userDataFile)) {
            userHandler.updateUserData(gson.fromJson(reader,User[].class));
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
