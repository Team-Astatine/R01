package teamzesa.configIOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler {
    private static ConfigIOHandler instance;
    private static File file;
    private static FileConfiguration config;

    private ConfigIOHandler(File file) {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static ConfigIOHandler getConfigIOHandler(File file) {
        if (instance == null)
            return  new ConfigIOHandler(file);
        return instance;
    }

    public void setMotd(String motd) {
        config.set("world_setting.motd",motd);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMotd() {
        String motd = config.getString("world_setting.motd");
        Bukkit.motd(ComponentExchanger.componentSet(motd));
    }
}
