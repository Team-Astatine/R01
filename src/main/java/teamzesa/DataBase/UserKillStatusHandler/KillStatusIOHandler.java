package teamzesa.DataBase.UserKillStatusHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import teamzesa.DataBase.entity.User;
import teamzesa.DataBase.entity.UserKillStatus;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.util.Enum.DataFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KillStatusIOHandler {
    public static void importKillStatusData(String reason) {
        Bukkit.getLogger().info("[R01] Importing " + DataFile.KILL_STATUS.getFileTypeName() + ".. " + reason);
        try (FileReader reader = new FileReader(DataFile.KILL_STATUS.getFileInstance())) {
            new KillStatusController().updateAllUserData(new Gson().fromJson(reader, UserKillStatus[].class));
        } catch (IOException e) {System.err.println("Import KillStatus Error");}
    }

    public static void exportKillStatusData(String reason) {
        Bukkit.getLogger().info("[R01] Exporting " + DataFile.KILL_STATUS.getFileTypeName() + ".. " + reason);
        try (FileWriter writer = new FileWriter(DataFile.KILL_STATUS.getFileInstance())) {
            new GsonBuilder().setPrettyPrinting().create()
                    .toJson(
                            new ArrayList<>(new KillStatusController().getAllUserTable().values()),
                            writer
                    );
        } catch (IOException e) {System.err.println("Export KillStatus Error");}
    }
}
