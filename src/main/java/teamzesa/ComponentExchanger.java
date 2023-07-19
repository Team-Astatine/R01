package teamzesa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public abstract class ComponentExchanger {

    private static Component component;

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
    }

    public static void serverAnnouncer(String string, String color) {
        component = Component.text(string)
                .color(TextColor.color(colorExchanger(color)));
        Bukkit.broadcast(component);
    }

    public static void playerAnnouncer(Player player, String string, String color) {
        component = Component.text(string)
                .color(TextColor.color(colorExchanger(color)));
        player.sendMessage(component);
    }

    public static Component componentSet(String string) {
        component = Component.text(string);
        return component;
    }

    public static Component componentSet(String string, String color) {
        component = Component.text(string)
                .color(TextColor.color(colorExchanger(color)));
        return component;
    }

    public static Component componentSet(StringBuilder string) {
        component = Component.text(String.valueOf(string));
        return component;
    }

    public static Component componentSet(StringBuilder string, String color) {
        component = Component.text(String.valueOf(string))
                .color(TextColor.color(colorExchanger(color)));
        return component;
    }
}
