package teamzesa.command.register;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Interface.EventRegister;
import teamzesa.util.Interface.ComponentExchanger;
import teamzesa.util.Enum.CommandExecutorMap;

public abstract class CommandRegister extends ComponentExchanger implements CommandExecutor {
    private final CommandExecutorMap typeOfCommand;

    public CommandRegister(CommandExecutorMap typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}
