package teamzesa.IOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigIOHandler {
    private static class ConfigIOHandlerHolder {
        private static ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }

    private ConfigIOHandler() {
    }

    public static ConfigIOHandler getConfigIOHandler() {
        return ConfigIOHandlerHolder.INSTANCE;
    }


}
