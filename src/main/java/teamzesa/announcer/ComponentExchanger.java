package teamzesa.announcer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ComponentExchanger {
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

    public static void serverAnnouncer(String string,String color) {
        component = Component.text(string)
                .color(colorExchanger(color));
        Bukkit.broadcast(component);
    }

    public static void playerAnnouncer(Player player, String string, String color) {
        component = Component.text(string)
                .color(colorExchanger(color));
        player.sendMessage(component);
    }

    public static Component componentSet(String string) {
        component = Component.text(string);
        return component;
    }

    public static Component componentSet(String string, String color) {
        component = Component.text(string)
                .color(colorExchanger(color));
        return component;
    }
}
