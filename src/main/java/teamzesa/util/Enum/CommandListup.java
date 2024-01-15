package teamzesa.util.Enum;

import org.bukkit.command.CommandExecutor;
import teamzesa.command.*;

public enum CommandListup {
    FLY("fly", new Fly()),
    MOTD("Motd", new MotdSet()),
    GOD_MODE("god", new GodModeSet()),
    ARMOUR_HEAD("hat", new ArmourSet()),
    ARMOUR_CHEST("몸통", new ArmourSet()),
    ARMOUR_LEGS("바지", new ArmourSet()),
    ARMOUR_FEET("신발", new ArmourSet()),
    MODERATOR("moderator", new Moderator()),
    HEALTH_RESET("setHealth", new HealthSet()),
    TOTEM_STACKING("totem", new TotemStacking()),
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
