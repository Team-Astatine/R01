package teamzesa.IOHandler.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.IOHandler;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler extends ComponentExchanger implements IOHandler {
    private static final String WORLD_SETTING_MOTD = "world_setting.motd";
    private static final String WORLD_SETTING_MINELIST = "world_setting.minelist";
    private static final String WORLD_SETTING_DISCORD = "world_setting.discord";
    private static final String WORLD_SETTING_NOTION = "world_setting.notion";

    private static class ConfigIOHandlerHolder {
        private static ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }

    private File file;
    private YamlConfiguration config;

    public static ConfigIOHandler getConfigIOHandler() {
        return ConfigIOHandlerHolder.INSTANCE;
    }

    @Override
    public void fileLoader(File configPathFile) {
        config = YamlConfiguration.loadConfiguration(configPathFile);
        file = configPathFile;
    }

    public void allConfigLoad() {
        worldConfigLoad();
        getMineListConfig();
        getDiscordConfig();
        getNotionConfig();
    }

    public void worldConfigLoad() {
        String motd = config.getString(WORLD_SETTING_MOTD);
        Bukkit.motd(componentSet(motd));
    }

    public String getMineListConfig() {
        return config.getString(WORLD_SETTING_MINELIST);
    }

    public String getDiscordConfig() {
        return config.getString(WORLD_SETTING_DISCORD);
    }

    public String getNotionConfig() {
        return config.getString(WORLD_SETTING_NOTION);
    }

    public void worldConfigSave(StringBuilder motd) {
        config.set(WORLD_SETTING_MOTD, String.valueOf(motd));
        try {
            config.save(file);
        } catch (IOException e) {
            // Consider logging this exception or alerting the user in a more friendly manner.
            e.printStackTrace();
        }
    }
}
