package teamzesa.command.register;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Enum.CommandType;
import teamzesa.util.Interface.StringComponentExchanger;

public abstract class CommandRegisterSection extends StringComponentExchanger implements /*TabCompleter,*/ CommandExecutor {
    private final CommandType typeOfCommand;

    public CommandRegisterSection(CommandType typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

//    @Override
//    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
//        return Collections.emptyList();
//    }
}
