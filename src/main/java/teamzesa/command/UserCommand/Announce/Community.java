package teamzesa.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.Data.DataIO.Config.ConfigIOHandler;
import teamzesa.Enumeration.Type.ColorType;

public record Community() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();

        String notionLink = configIOHandler.getNotionConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String mineListLink = configIOHandler.getMineListConfig();

        TextColor notionColor = ColorType.NOTION_COLOR.getTextColor();
        TextColor discordColor = ColorType.DISCORD_COLOR.getTextColor();
        TextColor mineListColor = ColorType.GREEN.getTextColor();

        commandSender.sendMessage(
                Component.text(configIOHandler.getMineListVote())
                        .color(mineListColor)
                        .clickEvent(ClickEvent.openUrl(mineListLink))
        );

        commandSender.sendMessage(
                Component.text(configIOHandler.getDiscordInvite())
                        .color(discordColor)
                        .clickEvent(ClickEvent.openUrl(discordLink))
        );

        commandSender.sendMessage(
                Component.text(configIOHandler.getServerGuideNotion())
                        .color(notionColor)
                        .clickEvent(ClickEvent.openUrl(notionLink))
        );

        return true;
    }
}
