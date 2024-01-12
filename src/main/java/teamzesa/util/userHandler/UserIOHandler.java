package teamzesa.util.userHandler;

import com.google.gson.*;
import org.bukkit.Bukkit;
import teamzesa.R01;
import teamzesa.entity.User;
import teamzesa.util.Enum.DataFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserIOHandler {
    private static class UserIOHandlerHolder {
        private static final UserIOHandler INSTANCE = new UserIOHandler();
    }

    private final UserController userController = new UserController();

    public static UserIOHandler getIOHandler() {
        return UserIOHandlerHolder.INSTANCE;
    }

    public void importUserData() {
        Bukkit.getLogger().info("[R01] Importing User Data..");
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(DataFile.USER_DATA.getFileInstance())) {
            this.userController.updateAllUserData(gson.fromJson(reader, User[].class));
        } catch (IOException e) {System.err.println("Import UserData Error");}
    }

    public void exportUserData() {
        Bukkit.getLogger().info("[R01] Exporting User Data..");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(DataFile.USER_DATA.getFileInstance())) {
            gson.toJson(
                    new ArrayList<>(this.userController.getAllUserTable().values()),
                    writer
            );
        } catch (IOException e) {System.err.println("Export UserData Error");}
    }
}