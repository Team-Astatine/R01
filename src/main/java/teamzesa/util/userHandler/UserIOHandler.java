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
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(DataFile.USER_DATA.getFileInstance())) {
            this.userController.updateAllUserData(gson.fromJson(reader, User[].class));
        } catch (IOException e) {System.err.println("InputUserData err");}
    }

    public void exportUserData() {
        Bukkit.getLogger().info("[R01] Saving User Data..");
        List<User> userData = new ArrayList<>(this.userController.getAllUserTable().values());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(DataFile.USER_DATA.getFileInstance())) {
            gson.toJson(userData, writer);
        } catch (IOException e) {System.err.println("ExportUserData err");}
    }
}