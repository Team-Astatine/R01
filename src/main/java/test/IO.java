package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IO {
    private Handler handler;
    private File files;

    public IO() {
        this.files = new File("C:\\Users\\JwonLEE\\Desktop\\userData.json");
        handler = new Handler();
    }

    public void saveFile() {
        List<User> userList = new ArrayList<>(handler.getMap().values());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try(FileWriter writer = new FileWriter(files)) {
            gson.toJson(userList,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputFile() {
        Gson gson = new Gson();
        try(FileReader fileReader = new FileReader(files)) {
            handler.updateData(gson.fromJson(fileReader,User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
