package teamzesa.command.Announcing;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandExecutorMap;

public class Help extends CommandRegisterSection {
    public Help() {
        super(CommandExecutorMap.HELP);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();

        playerSendMsgComponentExchanger((Player) commandSender, configIOHandler.getHelp(), ColorList.WHITE_TO_RED6);
        return true;
    }
}
