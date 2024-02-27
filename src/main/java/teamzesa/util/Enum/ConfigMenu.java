package teamzesa.util.Enum;

public enum ConfigMenu {
    WORLD_SETTING_MOTD("world_setting.motd"),
    WORLD_SETTING_MINELIST("world_setting.minelist"),
    WORLD_SETTING_DISCORD("world_setting.discord"),
    WORLD_SETTING_NOTION("world_setting.notion"),
    MESSAGE_MINELIST_VOTE("message.mineListVote"),
    MESSAGE_DISCORD_INVITE("message.discordInvite"),
    MESSAGE_SERVER_GUID_NOTION("message.serverGuideNotion"),
    MESSAGE_STEEL_LIFE_TIP("message.steelLifeTip"),
    MESSAGE_RAID_TIP("message.raidTip"),
    MESSAGE_WEAPON_TIP("message.weaponTip"),
    MESSAGE_EXPLOSIVE_TIP("message.explosiveTip"),
    MESSAGE_COMMAND_FLY("message.commandFly"),
    MESSAGE_COMMAND_HAT("message.commandHat"),
    MESSAGE_COMMAND_TOTEM("message.commandTotem"),
    MESSAGE_COMMAND_ANNOUNCING("message.announcing");

    private final String configMessage;

    ConfigMenu(String configMessage) {
        this.configMessage = configMessage;
    }

    public String getConfigMessage() {
        return configMessage;
    }
}
