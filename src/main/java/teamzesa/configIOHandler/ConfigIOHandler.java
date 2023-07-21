package teamzesa.configIOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler {
    private static class ConfigIOHandlerHolder {
        private static ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }
    private File file;
    private FileConfiguration config;

    private ConfigIOHandler() {
//        config = YamlConfiguration.loadConfiguration();
    }

    public static ConfigIOHandler getConfigIOHandler(File cofigFile) {
        return ConfigIOHandlerHolder.INSTANCE;
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
