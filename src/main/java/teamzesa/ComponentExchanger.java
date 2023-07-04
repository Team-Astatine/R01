package teamzesa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;

public abstract class ComponentExchanger {

    private static Component component;

    public static void serverAnnouncer(String string, java.awt.Color color) {
        component = Component.text(string)
                .color(TextColor.color((RGBLike) color));
        Bukkit.broadcast(component);
    }

    public static void playerAnnouncer(Player player, String string, Color color) {
        component = Component.text(string)
                .color(TextColor.color((RGBLike) color));
        player.sendMessage(component);
    }

    public static Component componentSet(String string) {
        component = Component.text(string);
        return component;
    }

    public static Component componentSet(String string, Color color) {
        component = Component.text(string)
                .color(TextColor.color((RGBLike) color));
        return component;
    }
}
