package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.Util.Function.StringComponentExchanger;

/**
 * 명령어를 등록하기 위한 구현체 클래스 입니다.
 * @see ListOfCommand 에 명령어 등록 후 해당 class를 extend 하여 구현하면 명령어 생성이 가능합니다.
 */
public abstract class CommandRegisterSection extends StringComponentExchanger implements /*TabCompleter,*/ CommandExecutor {
    private final ListOfCommand typeOfCommand;

    public CommandRegisterSection(ListOfCommand typeOfCommand) {
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
