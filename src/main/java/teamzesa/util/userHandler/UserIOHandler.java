package teamzesa.util.userHandler;

import com.google.gson.*;
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

    private final UserMapHandler userMapHandler;

    private UserIOHandler() {
        userMapHandler = UserMapHandler.getUserMapHandler();
    }

    public static UserIOHandler getIOHandler() {
        return UserIOHandlerHolder.INSTANCE;
    }

    public void importUserData() {
//        Gson gson = new GsonBuilder();
//                .registerTypeAdapter(User.class,new UserDeserializer())
//                .create();

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(DataFile.USER_DATA.getFileInstance())) {
            userMapHandler.updateAllUserData(gson.fromJson(reader, User[].class));
        } catch (IOException e) {
            System.err.println("InputUserData err");
            e.printStackTrace();
        }
    }

    public void exportUserData() {
        List<User> userData = new ArrayList<>(userMapHandler.getAllUserMap().values());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(DataFile.USER_DATA.getFileInstance())) {
            gson.toJson(userData, writer);
        } catch (IOException e) {
            System.err.println("OutPutUserData err");
            e.printStackTrace();
        }
    }
}

/*
class UserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject  jsonObject = json.getAsJsonObject();
        UUID uuid = UUID.fromString(jsonObject.get("uuid").getAsString());
        String      name = jsonObject.get("name").getAsString();
        Set<String> connectionIP = context.deserialize(jsonObject.getAsJsonArray("ip"), Set.class);
        int         joinCnt = jsonObject.get("joinCount").getAsInt();
        int         level = jsonObject.get("level").getAsInt();
        double      healthScale = jsonObject.get("healthScale").getAsDouble();
        boolean     godMode = jsonObject.get("godMode").getAsBoolean();

        return new User(uuid, name, connectionIP, joinCnt, level, healthScale, godMode);
    }
}
*/
