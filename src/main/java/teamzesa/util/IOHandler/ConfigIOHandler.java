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
        Bukkit.motd(componentExchanger(ConfigMenu.WORLD_SETTING_MOTD));
    }

    public String getWorldMotdConfig() {
        return ConfigMenu.WORLD_SETTING_MOTD.getConfigMessage();
    }

    public String getMineListConfig() {
        return ConfigMenu.WORLD_SETTING_MINELIST.getConfigMessage();
    }

    public String getDiscordConfig() {
        return ConfigMenu.WORLD_SETTING_DISCORD.getConfigMessage();
    }

    public String getNotionConfig() {
        return ConfigMenu.WORLD_SETTING_NOTION.getConfigMessage();
    }

    public String getMineListVote() {
        return ConfigMenu.MESSAGE_MINELIST_VOTE.getConfigMessage();
    }

    public String getDiscordInvite() {
        return ConfigMenu.MESSAGE_DISCORD_INVITE.getConfigMessage();
    }

    public String getServerGuideNotion() {
        return ConfigMenu.MESSAGE_SERVER_GUID_NOTION.getConfigMessage();
    }

    public String getSteelLifeTip() {
        return ConfigMenu.MESSAGE_STEEL_LIFE_TIP.getConfigMessage();
    }

    public String getRaidTip() {
        return ConfigMenu.MESSAGE_RAID_TIP.getConfigMessage();
    }

    public String getWeaponTip() {
        return ConfigMenu.MESSAGE_WEAPON_TIP.getConfigMessage();
    }

    public String getExplosiveTip() {
        return ConfigMenu.MESSAGE_EXPLOSIVE_TIP.getConfigMessage();
    }

    public String getCommandFly() {
        return ConfigMenu.MESSAGE_COMMAND_FLY.getConfigMessage();
    }

    public String getCommandHat() {
        return ConfigMenu.MESSAGE_COMMAND_HAT.getConfigMessage();
    }

    public String getCommandTotem() {
        return ConfigMenu.MESSAGE_COMMAND_TOTEM.getConfigMessage();
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
