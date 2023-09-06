package teamzesa.IOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.ComponentExchanger;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler extends ComponentExchanger implements IOHandler {
    private static final String WORLD_SETTING_MOTD = "world_setting.motd";
    private static final String WORLD_SETTING_MINELIST = "world_setting.minelist";
    private static final String WORLD_SETTING_DISCORD = "world_setting.discord";
    private static final String WORLD_SETTING_NOTION = "world_setting.notion";

    private static class ConfigIOHandlerHolder {
        private static final ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }

    private File file;
    private YamlConfiguration config;

    public static ConfigIOHandler getConfigIOHandler() {
        return ConfigIOHandlerHolder.INSTANCE;
    }

    @Override
    public void fileLoader(File configPathFile) {
        file = configPathFile;
        config = YamlConfiguration.loadConfiguration(configPathFile);
    }

    public void allConfigLoad() {
        setWorldSettingMotd();
        getMineListConfig();
        getDiscordConfig();
        getNotionConfig();
    }

    public void setWorldSettingMotd() {
        Bukkit.motd(
                componentSet(config.getString(WORLD_SETTING_MOTD))
        );
    }

    public String getWorldMotdConfig() {
        return config.getString(WORLD_SETTING_MOTD);
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

    public void worldConfigSave(String motd) {
        config.set(WORLD_SETTING_MOTD, motd);
        try {
            config.save(file);
        } catch (IOException e) {
            // Consider logging this exception or alerting the user in a more friendly manner.
            e.printStackTrace();
        }
    }
}
