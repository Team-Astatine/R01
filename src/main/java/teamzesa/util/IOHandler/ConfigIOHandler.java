package teamzesa.util.IOHandler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ConfigMenu;
import teamzesa.util.Enum.DataFile;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler extends ComponentExchanger {
    private static class ConfigIOHandlerHolder {
        private static final ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }

    private File file;
    private YamlConfiguration config;

    public static ConfigIOHandler getConfigIOHandler() {
        return ConfigIOHandlerHolder.INSTANCE;
    }

    public void fileLoader() {
        this.file = DataFile.CONFIG.getFileInstance();
        this.config = YamlConfiguration.loadConfiguration(DataFile.CONFIG.getFileInstance());
    }

    public String getString(ConfigMenu msgPath) {
        return this.config.getString(msgPath.getConfigMessage());
    }

    public void allConfigLoad() {
        setWorldSettingMotd();
        getMineListConfig();
        getDiscordConfig();
        getNotionConfig();
        getMineListVote();
        getDiscordInvite();
        getServerGuideNotion();
        getSteelLifeTip();
        getRaidTip();
        getWeaponTip();
        getExplosiveTip();
        getCommandFly();
        getCommandHat();
        getCommandTotem();
    }

    public void setWorldSettingMotd() {
        Bukkit.motd(componentExchanger(getString(ConfigMenu.WORLD_SETTING_MOTD)));
    }

    public String getWorldMotdConfig() {
        return getString(ConfigMenu.WORLD_SETTING_MOTD);
    }

    public String getMineListConfig() {
        return getString(ConfigMenu.WORLD_SETTING_MINELIST);
    }

    public String getDiscordConfig() {
        return getString(ConfigMenu.WORLD_SETTING_DISCORD);
    }

    public String getNotionConfig() {
        return getString(ConfigMenu.WORLD_SETTING_NOTION);
    }

    public String getMineListVote() {
        return getString(ConfigMenu.MESSAGE_MINELIST_VOTE);
    }

    public String getDiscordInvite() {
        return getString(ConfigMenu.MESSAGE_DISCORD_INVITE);
    }

    public String getServerGuideNotion() {
        return getString(ConfigMenu.MESSAGE_SERVER_GUID_NOTION);
    }

    public String getSteelLifeTip() {
        return getString(ConfigMenu.MESSAGE_STEEL_LIFE_TIP);
    }

    public String getRaidTip() {
        return getString(ConfigMenu.MESSAGE_RAID_TIP);
    }

    public String getWeaponTip() {
        return getString(ConfigMenu.MESSAGE_WEAPON_TIP);
    }

    public String getExplosiveTip() {
        return getString(ConfigMenu.MESSAGE_EXPLOSIVE_TIP);
    }

    public String getCommandFly() {
        return getString(ConfigMenu.MESSAGE_COMMAND_FLY);
    }

    public String getCommandHat() {
        return getString(ConfigMenu.MESSAGE_COMMAND_HAT);
    }

    public String getCommandTotem() {
        return getString(ConfigMenu.MESSAGE_COMMAND_TOTEM);
    }

    public void worldConfigSave(String motd) {
        this.config.set(ConfigMenu.WORLD_SETTING_MOTD.getConfigMessage(), motd);
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            System.err.println("Saving World Config Fail!");
        }
    }
}
