package teamzesa.command.register;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.Collections;
import java.util.List;

public abstract class CommandRegisterSection extends StringComponentExchanger implements /*TabCompleter,*/ CommandExecutor {
    private final CommandExecutorMap typeOfCommand;

    public CommandRegisterSection(CommandExecutorMap typeOfCommand) {
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
