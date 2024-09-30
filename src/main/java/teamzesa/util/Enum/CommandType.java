package teamzesa.util.Enum;

import org.bukkit.command.CommandExecutor;
import teamzesa.command.AdminCommand.*;
import teamzesa.command.Announcing.CommandTip;
import teamzesa.command.Announcing.Community;
import teamzesa.command.Announcing.Help;
import teamzesa.command.Announcing.ServerTip;
import teamzesa.command.*;

public enum CommandType {
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
    EXPORT_DATA_FILE("ExportDataFile", new ExportDataFile()),
    REMOVE_DUPLICATE_USER_DATA("RemoveDuplicateData", new RemoveDuplicateData()),
    HEALTH_RESET("setHealth", new SetHealth()),
    GOD_MODE("god", new God()),
    CONFIG_RELOAD("dataFileReload", new DataFileReload()),
    LOOK_USER_VALUE("vo", new LookUserValue()),
    LOOK_USER_MAIN_HAND_ITEM("vi", new LookUserMainHandItem()),
    ENHANCE_SET("enhance", new EnhanceSet());

    private final String command;
    private final CommandExecutor executor;

    CommandType(String command, CommandExecutor executor) {
        this.command = command;
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public CommandExecutor newCommandExecutor() {
        return executor;
    }

//    public TabCompleter newTabCompleterInstance() {
//        return executor;
//    }
}
