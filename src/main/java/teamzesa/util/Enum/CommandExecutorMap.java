package teamzesa.util.Enum;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import teamzesa.command.*;
import teamzesa.command.AdminCommand.*;
import teamzesa.command.register.CommandRegisterSection;

public enum CommandExecutorMap {
    FLY("fly", new Fly()),
    MOTD("motd", new Motd()),
    GOD_MODE("god", new God()),
    ARMOUR_HEAD("hat", new Hat()),
    MODERATOR("moderator", new Moderator()),
    HEALTH_RESET("setHealth", new SetHealth()),
    TOTEM_STACKING("totem", new Totem()),
    USER_OBJECT_CHECKER("vo", new ValueObjectChecker()),
    HAND_ITEM_CHECKER("vi", new ValueItemStackChecker()),
    SAVE_R01_OBJECT_DATA("ExportR01Object", new SaveR01ObjectData()),
    CONFIG_RELOAD("dataFileReload", new DataFileReload()),
    ANNOUNCING("공지", new AnnouncingOnOff()),
    ENHANCE("강화", new EnhanceDialog()),
    ENHANCE_SET("enhance", new EnhanceSet()),
    REMOVE_DUPLICATE_USER_DATA("RemoveDuplicateData", new RemoveDuplicateData());

    private final String command;
    private final CommandRegisterSection executor;

    CommandExecutorMap(String command, CommandRegisterSection executor) {
        this.command = command;
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public CommandExecutor newCommandExecutorInstance() {
        return executor;
    }

//    public TabCompleter newTabCompleterInstance() {
//        return executor;
//    }
}
