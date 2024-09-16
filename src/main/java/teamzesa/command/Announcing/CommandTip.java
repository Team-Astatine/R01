package teamzesa.command.Announcing;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.ArrayList;

public class CommandTip extends CommandRegisterSection {

    public CommandTip() {
        super(CommandExecutorMap.COMMAND_TIP);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        ColorMap commandColor = ColorMap.COMMAND_COLOR;
        ColorMap enhanceColor = ColorMap.PINK;

        ArrayList<Component> commandTip = new ArrayList<>();
        commandTip.add(componentExchanger(configIOHandler.getCommandEnhance(), enhanceColor));
        commandTip.add(componentExchanger(configIOHandler.getCommandFly(), commandColor));
        commandTip.add(componentExchanger(configIOHandler.getCommandHat(), commandColor));
        commandTip.add(componentExchanger(configIOHandler.getCommandTotem(), commandColor));
        commandTip.add(componentExchanger(configIOHandler.getCommandAnnouncing(), commandColor));
        commandTip.add(componentExchanger(configIOHandler.getCommandTpa(), commandColor));

        for (Component comment : commandTip)
            playerSendMsgComponentExchanger((Player) commandSender, comment);

        return true;
    }
}
