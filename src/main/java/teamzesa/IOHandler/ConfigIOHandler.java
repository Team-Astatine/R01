package teamzesa.IOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void configLoader(File filePath) {
        file = filePath;
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void allConfigLoad() {
        worldConfigLoad();
    }

    private void worldConfigLoad() {
        ConfigurationSection configMotd = config.getConfigurationSection("world_setting");
        String motd = configMotd.getString("motd");
        Bukkit.motd(componentSet(motd));
    }

    public void worldConfigSave(StringBuilder motd) {
        worldConfigSave(String.valueOf(motd));
    }

    public void worldConfigSave(String motd) {
        ConfigurationSection configMotd = config.getConfigurationSection("world_setting");
        configMotd.set("motd",motd);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
