package teamzesa.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.Data.DataIO.Config.ConfigIOHandler;
import teamzesa.Enumeration.Type.ColorType;

import java.util.ArrayList;

public record CommandTip() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        TextColor commandColor = ColorType.COMMAND_COLOR.getTextColor();
        TextColor enhanceColor = ColorType.PINK.getTextColor();

        ArrayList<Component> commandTip = new ArrayList<>();
        commandTip.add(Component.text(configIOHandler.getCommandEnhance(), enhanceColor));
        commandTip.add(Component.text(configIOHandler.getCommandFly(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandHat(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandSwap(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandTotem(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandAnnouncing(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandTpa(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandShop(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandBalance(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandParty(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandSkill(), commandColor));
        commandTip.add(Component.text(configIOHandler.getCommandPlayTime(), commandColor));

        for (Component comment : commandTip)
            commandSender.sendMessage(comment);

        return true;
    }
}
