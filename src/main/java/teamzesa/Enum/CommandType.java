package teamzesa.Enum;

import org.bukkit.command.CommandExecutor;
import teamzesa.command.ModeratorCommand.*;
import teamzesa.command.Announcing.CommandTip;
import teamzesa.command.Announcing.Community;
import teamzesa.command.Announcing.Help;
import teamzesa.command.Announcing.ServerTip;
import teamzesa.command.*;

public enum CommandType {
//    User
    ASTN("Astn", new TitleAstatine()),
    HELP("Help", new Help()),
    MODERATOR("moderator", new Moderator()),
    ARMOUR_HEAD("hat", new SwapMainHandItemToHatInvItem()),
    OFF_HAND_ITEM_SWAP_FOR_BE("swap", new OffHandItemSwapFunction()),
    TOTEM_STACKING("totem", new StackingTotemFunction()),
    FLY("fly", new ToggleFly()),
    ANNOUNCING("공지", new ToggleAnnouncing()),
    ENHANCE("강화", new OpenEnhanceDialog()),
    COMMAND_TIP("명령어", new CommandTip()),
    SERVER_TIP("서버팁", new ServerTip()),
    COMMUNITY("커뮤니티", new Community()),

//    Moderator
    MOTD("motd", new SetMotd()),
    EXPORT_DATA_FILE("ExportDataFile", new ExportAllData()),
    REMOVE_DUPLICATE_USER_DATA("RemoveDuplicateData", new RemoveDuplicateUserNameData()),
    HEALTH_RESET("setHealth", new SetHealth()),
    GOD_MODE("god", new SetGodMode()),
    CONFIG_RELOAD("dataFileReload", new ConfigDataReload()),
    LOOK_USER_VALUE("vo", new LookUserValue()),
    LOOK_USER_MAIN_HAND_ITEM("vi", new LookUserMainHandItem()),
    ENHANCE_SET("enhance", new SetEnhance());

    private final String command;
    private final CommandExecutor executor;

    CommandType(String command, CommandExecutor executor) {
        this.command = command;
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public CommandExecutor getCommandInstance() {
        return executor;
    }

//    public TabCompleter newTabCompleterInstance() {
//        return executor;
//    }
}
