package teamzesa.util.Enum;

import org.bukkit.command.CommandExecutor;
import teamzesa.command.*;

public enum CommandExecutorMap {
    FLY("fly", new Fly()),
    MOTD("motd", new Motd()),
    GOD_MODE("god", new God()),
    ARMOUR_HEAD("hat", new Hat()),
    MODERATOR("moderator", new Moderator()),
    HEALTH_RESET("setHealth", new SetHealth()),
    TOTEM_STACKING("totem", new Totem()),
    USER_OBJECT_CHECKER("vo", new ValueObjectChecker()),
    SAVE_R01_OBJECT_DATA("ExportR01Object", new SaveR01ObjectData()),
    CONFIG_RELOAD("dataFileReload", new DataFileReload()),
    ANNOUNCING("공지", new AnnouncingOnOff()),
    ENHANCE("강화", new EnhanceStuff()),
    ENHANCE_SET("enhance", new EnhnaceSet());

    private final String command;
    private final CommandExecutor executor;

    CommandExecutorMap(String command, CommandExecutor executor) {
        this.command = command;
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public CommandExecutor newInstance() {
        return executor;
    }
}
