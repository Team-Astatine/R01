package teamzesa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ComponentExchanger {

    private static TextColor colorExchanger(@NotNull Color color) {
//        Ensure 24-bit RGB value
        int rgb = color.getRGB() & 0xFFFFFF;
        return TextColor.color(rgb);
    }

    public static Component createLinkComponent(String text, String url, Color color) {
        TextComponent.Builder component = Component.text()
                .content(text)
                .color(colorExchanger(color))
                .clickEvent(ClickEvent.openUrl(url));
        return component.build();
    }

    public static void serverAnnouncer(String string, Color color) {
        Component component = Component.text(string)
                .color(colorExchanger(color));
        Bukkit.broadcast(component);
    }

    public static void playerAnnouncer(@NotNull Player player, String string, Color color) {
        Component component = Component.text(string)
                .color(colorExchanger(color));
        player.sendMessage(component);
    }

    public static void playerAnnouncer(@NotNull Player player, String string) {
        player.sendMessage(Component.text(string));
    }

    public static Component componentSet(String string) {
        return Component.text(string);
    }

    public static Component componentSet(String string, Color color) {
        return Component.text(string)
                .color(colorExchanger(color));
    }
}
