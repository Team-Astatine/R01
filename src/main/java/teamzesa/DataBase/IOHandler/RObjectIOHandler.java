package teamzesa.DataBase.IOHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import teamzesa.DataBase.entity.RObject;
import teamzesa.util.Enum.DataFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RObjectIOHandler {

    public <E extends RObject> ArrayList <E> importData(DataFile dataFile, Class<E> toCastClass, String affiliatedFunction) {
        loggingConsole(dataFile.getFileTypeName(), affiliatedFunction, false, true);
        ArrayList<E> resultData = new ArrayList<>();

        try (FileReader reader = new FileReader(dataFile.getFileInstance())) {
            resultData = new Gson().fromJson(reader, TypeToken.getParameterized(ArrayList.class, toCastClass).getType());
        } catch (IOException e) {
            loggingConsole(dataFile.getFileTypeName(), affiliatedFunction, true, true);
            e.printStackTrace();
        }
        return resultData;
    }

    public <E extends RObject> void exportData(DataFile dataFile, ArrayList<E> totalDataValue, String affiliatedFunction) {
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
