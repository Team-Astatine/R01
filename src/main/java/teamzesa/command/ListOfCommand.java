package teamzesa.command;

import org.bukkit.command.CommandExecutor;
import teamzesa.R01;
import teamzesa.command.ModeratorCommand.*;
import teamzesa.command.UserCommand.Announce.*;
import teamzesa.command.UserCommand.Function.*;
import teamzesa.Event.UserInterface.GSit.GSitUI;
import teamzesa.Event.UserInterface.Enhance.EnhanceUI;

/**
 * 명령어 추가 시 해당 Enumeration 에 추가합니다.
 * 관리자 명령어와, 유저 명령어를 구분 후 추가해야 하며, 각 명령어에 permission 설정을 통해 Tab Complete 에 제한을 둬야합니다.
 *
 * 명령어 추가방법
 * resources/plugin.yml 에 명령어 추가합니다.
 * @see ListOfCommand 에 명령어 등록 및 명령 시 발생할 Instance 추가합니다.
 * @see CommandRegisterSection 을 extend하여 명령어 입력 시 execute 할 Instance를 추가합니다.
 *
 * @implSpec {@link R01} 에 registerCommandAndEvent() 함수에서 명령어를 일괄 등록합니다.
 */
public enum ListOfCommand {
//    User
    ASTN("Astn", new TitleAstatine()),
    HELP("Help", new Help()),
    MODERATOR("moderator", new Moderator()),
    ARMOUR_HEAD("hat", new SwapMainHandItemToHatInvItem()),
    OFF_HAND_ITEM_SWAP_FOR_BE("swap", new OffHandItemSwapFunction()),
    TOTEM_STACKING("totem", new StackingTotemFunction()),
    FLY("fly", new ToggleFly()),
    TIME("playtime", new PlayTime()),
    GSIT_ACTIONS("gsitActioUI", new GSitUI()),
    ANNOUNCING("공지", new ToggleAnnouncing()),
    ENHANCE("강화", new EnhanceUI()),
    COMMAND_TIP("명령어", new CommandTip()),
    SERVER_TIP("서버팁", new ServerTip()),
    COMMUNITY("커뮤니티", new Community()),

//    Moderator
    MOTD_SET("motd", new SetMotd()),
    EXPORT_DATA_FILE("ExportDataFile", new ExportAllData()),
    REMOVE_DUPLICATE_USER_DATA("RemoveDuplicateData", new RemoveDuplicateUserNameData()),
    HEALTH_SCALE_SET("setHealth", new SetHealth()),
    GOD_MODE_SET("god", new SetGodMode()),
    CONFIG_RELOAD("dataFileReload", new ConfigDataReload()),
    LOOK_USER_VALUE("vo", new LookUserValue()),
    LOOK_USER_MAIN_HAND_ITEM("vi", new LookUserMainHandItem()),
    ENHANCE_SET("enhance", new SetEnhance()),
    KILL_COUNT_SET("setKillCount", new SetKillCount());

    private final String command;
    private final CommandExecutor executor;

    ListOfCommand(String command, CommandExecutor executor) {
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
