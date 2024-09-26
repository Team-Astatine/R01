package teamzesa.command.Announcing;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.util.Enum.ColorList;

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

        TextColor notionColor = ColorList.NOTION_COLOR.getTextColor();
        TextColor discordColor = ColorList.DISCORD_COLOR.getTextColor();
        TextColor mineListColor = ColorList.GREEN.getTextColor();

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
