package teamzesa.command.Announcing;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.util.Enum.ColorList;

import java.util.ArrayList;

public record CommandTip() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        TextColor commandColor = ColorList.COMMAND_COLOR.getTextColor();
        TextColor enhanceColor = ColorList.PINK.getTextColor();

        ArrayList<Component> commandTip = new ArrayList<>();
        commandTip.add(Component.text(configIOHandler.getCommandEnhance(), enhanceColor));
        commandTip.add(Component.text(configIOHandler.getCommandFly(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandHat(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandSwap(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandTotem(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandAnnouncing(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandTpa(), commandColor));

        for (Component comment : commandTip)
            commandSender.sendMessage(comment);

        return true;
    }
}
