package teamzesa.announcer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;

public abstract class ComponentExchanger {

    private static Component component;

    /*Color Testing
    private static TextColor colorExchanger(String color) {
        if (color.equals("RED"))
            return TextColor.color(0xF80040);

        if (color.equals("YELLOW"))
            return TextColor.color(0xF8F700);

        if (color.equals("ORANGE"))
            return TextColor.color(0xF89935);

        if (color.equals("WHITE"))
            return TextColor.color(0xF8F2F8);

        return null;
    }*/

    /*enum Testing
    enum Color {
        RED(0xF80040),
        YELLOW(0xF8F700),
        ORANGE(0xF89935),
        WHITE(0xF8F2F8);

        private final int colorCode;

        Color(int hexCode) {
            this.colorCode = hexCode;
        }

        public TextColor getTextColor() {
            return TextColor.color(colorCode);
        }
    }*/

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
