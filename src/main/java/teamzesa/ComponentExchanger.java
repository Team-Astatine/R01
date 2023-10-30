package teamzesa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import teamzesa.dataValue.ColorList;

public class ComponentExchanger {

    public static @NotNull Component createLinkComponent(String text, String url, @NotNull ColorList color) {
        TextComponent.Builder component = Component.text()
                .content(text)
                .color(color.getTextColor())
                .clickEvent(ClickEvent.openUrl(url));
        return component.build();
    }

    public static void serverAnnouncer(String comment, @NotNull ColorList color) {
        Component component = Component.text(comment)
                .color(color.getTextColor());
        Bukkit.broadcast(component);
    }

    public static void playerAnnouncer(@NotNull Player player, String comment, @NotNull ColorList color) {
        Component component = Component.text(comment)
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public static void playerAnnouncer(@NotNull Player player, @NotNull StringBuilder comment, @NotNull ColorList color) {
        Component component = Component.text(comment.toString())
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public static void playerAnnouncer(@NotNull CommandSender sender, @NotNull String comment, @NotNull ColorList color) {
        Player player = (Player) sender;
        Component component = Component.text(comment)
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public static void playerAnnouncer(@NotNull CommandSender sender, @NotNull StringBuilder comment, @NotNull ColorList color) {
        Player player = (Player) sender;
        Component component = Component.text(comment.toString())
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public static void playerAnnouncer(@NotNull Player player, String string) {
        player.sendMessage(Component.text(string));
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Component componentSet(String comment) {
        return Component.text(comment);
    }

    public static @NotNull Component componentSet(String comment, @NotNull ColorList color) {
        return Component.text(comment)
                .color(color.getTextColor());
    }
}
