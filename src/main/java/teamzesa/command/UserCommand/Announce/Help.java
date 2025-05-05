package teamzesa.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.Data.DataIO.Config.ConfigIOHandler;
import teamzesa.Enumeration.Type.ColorType;

public record Help() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        commandSender.sendMessage(Component.text(
                configIOHandler.getHelp(), ColorType.WHITE_TO_RED6.getTextColor())
        );
        return true;
    }
}
