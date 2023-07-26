package teamzesa.IOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler extends ComponentExchanger {
    private static class ConfigIOHandlerHolder {
        private static ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }

    private static File file;
    private static YamlConfiguration config;

    public static ConfigIOHandler getConfigIOHandler() {
        return ConfigIOHandlerHolder.INSTANCE;
    }

    public void configLoader(File configPathFile) {
        config = YamlConfiguration.loadConfiguration(configPathFile);
        file = configPathFile;
    }

    public void allConfigLoad() {
        worldConfigLoad();
    }

    public void worldConfigLoad() {
        String motd = config.getString("world_setting.motd");
        Bukkit.motd(componentSet(motd));
    }

    public void worldConfigSave(StringBuilder motd) {
        worldConfigSave(String.valueOf(motd));
    }

    public void worldConfigSave(String motd) {
        config.set("world_setting.motd",motd);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
