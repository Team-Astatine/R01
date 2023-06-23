package teamzesa;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.*;
import teamzesa.command.NameChanger;
import teamzesa.announcer.RaidAnnouncer;
import teamzesa.command.TotemStacking;
import teamzesa.user.User;
import teamzesa.user.UserHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;
    private final UserHandler userHandler;
    private final File userDataFile;

    public R01() {
        pm = getServer().getPluginManager();
        userHandler = UserHandler.getInstance();
        userDataFile = new File(this.getDataFolder(), "userData.json");
    }

    private void commandHandler() {
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("nameChanger").setExecutor(new NameChanger());
    }

    private void inputUserData() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(userDataFile)) {
            userHandler.updateUserData(gson.fromJson(reader,User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outputUserData() {
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

    @Override
    public void onEnable() {
        inputUserData();  // user Data Set
        commandHandler(); // command set

        pm.registerEvents(this,this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new UserJoinHandler(),this);
        pm.registerEvents(new ExplosiveHandler(),this);
        pm.registerEvents(new UserDyingHandler(),this);
        pm.registerEvents(new UserHealthScaleHandler(),this);
        pm.registerEvents(new EntityDamageTickingHandler(),this);
    }

    @Override
    public void onDisable() {
        outputUserData();
    }
}
