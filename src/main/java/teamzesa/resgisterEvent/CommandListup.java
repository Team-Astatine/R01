package teamzesa.resgisterEvent;

import org.bukkit.command.CommandExecutor;
import teamzesa.IOHandler.DataFile;
import teamzesa.command.*;

public enum CommandListup {
    FLY("fly", new Fly()),
    METEOR("운석", new Meteor()),
    MOTD("Motd", new MotdSet()),
    ARMOUR_HEAD("머리", new ArmourSet()),
    ARMOUR_CHEST("몸통", new ArmourSet()),
    ARMOUR_LEGS("바지", new ArmourSet()),
    ARMOUR_FEET("신발", new ArmourSet()),
    GOD_MODE("god", new GodModeSet()),
    MODERATOR("관리자", new Moderator()),
    TOTEM_STACKING("토템", new TotemStacking()),
    HEALTH_RESET("체력초기화", new HealthSet()),
    USER_OBJECT_CHECKER("나", new UserObjectChecker()),
    SAVE_USER_DATA("SaveUserData", new SaveUserData()),
    CONFIG_RELOAD("dataFileRelaod", new Reload());

    private final String command;
    private final CommandExecutor executor;

    CommandListup(String command, CommandExecutor executor) {
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
