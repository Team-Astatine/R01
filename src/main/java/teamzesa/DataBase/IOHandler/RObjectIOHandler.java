package teamzesa.DataBase.IOHandler;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import teamzesa.DataBase.entity.User;
import teamzesa.util.Enum.DataFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RObjectIOHandler<E> {
    private final Type listType;

    public RObjectIOHandler() {
        this.listType = new TypeToken<ArrayList<E>>(){}.getType();
    }

    public ArrayList<E> importData(DataFile dataFile, String affiliatedFunction) {
        loggingConsole(dataFile.getFileTypeName(), affiliatedFunction, false, true);
        ArrayList<E> resultData = new ArrayList<>();

        try (FileReader reader = new FileReader(dataFile.getFileInstance())) {
            resultData = new Gson().fromJson(reader, this.listType);
        } catch (IOException e) {
            loggingConsole(dataFile.getFileTypeName(), affiliatedFunction, true, true);
            e.printStackTrace();
        }

        return resultData;
    }

    public void exportData(DataFile dataFile, String affiliatedFunction, ArrayList<E> totalDataValue) {
        loggingConsole(dataFile.getFileTypeName(), affiliatedFunction, false, false);

        try (FileWriter writer = new FileWriter(dataFile.getFileInstance())) {
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(totalDataValue,writer);

        } catch (IOException e) {
            loggingConsole(dataFile.getFileTypeName(), affiliatedFunction, true, false);
            e.printStackTrace();
        }
    }

    private void loggingConsole(String fileName, String affiliatedFunction, boolean isError, boolean isImporting) {
        String importComment = isImporting ? "Importing" : "Exporting";
        if (!isError)
            Bukkit.getLogger().info("[R01]" + importComment + " " + fileName + ".. " + affiliatedFunction);
        else if (isError)
            Bukkit.getLogger().info("[R01]" + importComment + " " + fileName + ".. " + affiliatedFunction + "Error");
    }
}
