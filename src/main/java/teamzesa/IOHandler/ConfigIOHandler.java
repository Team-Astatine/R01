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

    private static final String MESSAGE_MINELIST_VOTE = "message.mineListVote";
    private static final String MESSAGE_DISCORD_INVITE = "message.discordInvite";
    private static final String MESSAGE_SERVER_GUID_NOTION = "message.serverGuideNotion";
    private static final String MESSAGE_STEEL_LIFE_TIP = "message.steelLifeTip";
    private static final String MESSAGE_RAID_TIP = "message.raidTip";
    private static final String MESSAGE_WEAPON_TIP = "message.weaponTip";
    private static final String MESSAGE_EXPLOSIVE_TIP = "message.explosiveTip";
    private static final String MESSAGE_COMMAND_FLY = "message.commandFly";
    private static final String MESSAGE_COMMAND_HAT = "message.commandHat";
    private static final String MESSAGE_COMMAND_TOTEM = "message.commandTotem";


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

    public String getMineListVote() {
        return config.getString(MESSAGE_MINELIST_VOTE);
    }

    public String getDiscordInvite() {
        return config.getString(MESSAGE_DISCORD_INVITE);
    }

    public String getServerGuideNotion() {
        return config.getString(MESSAGE_SERVER_GUID_NOTION);
    }

    public String getSteelLifeTip() {
        return config.getString(MESSAGE_STEEL_LIFE_TIP);
    }

    public String getRaidTip() {
        return config.getString(MESSAGE_RAID_TIP);
    }

    public String getWeaponTip() {
        return config.getString(MESSAGE_WEAPON_TIP);
    }

    public String getExplosiveTip() {
        return config.getString(MESSAGE_EXPLOSIVE_TIP);
    }

    public String getCommandFly() {
        return config.getString(MESSAGE_COMMAND_FLY);
    }

    public String getCommandHat() {
        return config.getString(MESSAGE_COMMAND_HAT);
    }

    public String getCommandTotem() {
        return config.getString(MESSAGE_COMMAND_TOTEM);
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
