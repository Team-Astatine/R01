package teamzesa.DataBase.userHandler;

import com.google.gson.*;
import org.bukkit.Bukkit;
import teamzesa.entity.User;
import teamzesa.util.Enum.DataFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserIOHandler {

    public static void importUserData(String reason) {
        Bukkit.getLogger().info("[R01] Importing " + DataFile.USER_DATA.getFileTypeName() + ".. " + reason);
        try (FileReader reader = new FileReader(DataFile.USER_DATA.getFileInstance())) {
            new UserController().updateAllUserData(new Gson().fromJson(reader, User[].class));
        } catch (IOException e) {System.err.println("Import UserData Error");}
    }

    public static void exportUserData(String reason) {
        Bukkit.getLogger().info("[R01] Exporting " + DataFile.USER_DATA.getFileTypeName() + ".. " + reason);
        try (FileWriter writer = new FileWriter(DataFile.USER_DATA.getFileInstance())) {
            new GsonBuilder().setPrettyPrinting().create()
                    .toJson(
                        new ArrayList<>(new UserController().getAllUserTable().values()),
                        writer
                    );
        } catch (IOException e) {System.err.println("Export UserData Error");}
    }
}