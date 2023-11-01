package teamzesa.resgisterEvent;

import org.bukkit.command.CommandExecutor;
import teamzesa.IOHandler.DataFile;
import teamzesa.command.*;

public enum CommandListup {
    FLY("fly", new Fly()),
    METEOR("meteor", new Meteor()),
    MOTD("Motd", new MotdSet()),
    ARMOUR_HEAD("hat", new ArmourSet()),
    ARMOUR_CHEST("몸통", new ArmourSet()),
    ARMOUR_LEGS("바지", new ArmourSet()),
    ARMOUR_FEET("신발", new ArmourSet()),
    GOD_MODE("god", new GodModeSet()),
    MODERATOR("moderator", new Moderator()),
    TOTEM_STACKING("totem", new TotemStacking()),
    HEALTH_RESET("setHealth", new HealthSet()),
    USER_OBJECT_CHECKER("vo", new UserObjectChecker()),
    SAVE_USER_DATA("SaveUserData", new SaveUserData()),
    CONFIG_RELOAD("dataFileReload", new Reload());

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
