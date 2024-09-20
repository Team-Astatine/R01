package teamzesa.util.Enum;

import org.bukkit.command.CommandExecutor;
import teamzesa.command.AdminCommand.*;
import teamzesa.command.Announcing.CommandTip;
import teamzesa.command.Announcing.Community;
import teamzesa.command.Announcing.Help;
import teamzesa.command.Announcing.ServerTip;
import teamzesa.command.*;
import teamzesa.command.register.CommandRegisterSection;

public enum CommandExecutorMap {
//    User
    ASTN("Astn", new Astn()),
    HELP("Help", new Help()),
    MODERATOR("moderator", new Moderator()),
    ARMOUR_HEAD("hat", new Hat()),
    TOTEM_STACKING("totem", new Totem()),
    FLY("fly", new Fly()),
    ANNOUNCING("공지", new AnnouncingOnOff()),
    ENHANCE("강화", new EnhanceDialog()),
    COMMAND_TIP("명령어", new CommandTip()),
    SERVER_TIP("서버팁", new ServerTip()),
    COMMUNITY("커뮤니티", new Community()),

//    Moderator
    MOTD("motd", new Motd()),
    SAVE_R01_OBJECT_DATA("ExportR01Object", new SaveR01ObjectData()),
    REMOVE_DUPLICATE_USER_DATA("RemoveDuplicateData", new RemoveDuplicateData()),
    HEALTH_RESET("setHealth", new SetHealth()),
    GOD_MODE("god", new God()),
    CONFIG_RELOAD("dataFileReload", new DataFileReload()),
    USER_OBJECT_CHECKER("vo", new ValueObjectChecker()),
    HAND_ITEM_CHECKER("vi", new ValueItemStackChecker()),
    ENHANCE_SET("enhance", new EnhanceSet());

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
