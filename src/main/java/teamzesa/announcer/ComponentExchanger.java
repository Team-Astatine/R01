package teamzesa.announcer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ComponentExchanger {

    private static ComponentExchanger instance;
    private static Component component;

    public static ComponentExchanger getAnnouncerHandler() {
        if (instance == null)
            return new ComponentExchanger();
        return instance;
    }

    public static void serverAnnouncer(String string,Integer color) {
        component = Component.text(string)
                .color(TextColor.color(color));

        Bukkit.broadcast(component);
    }

    public static void playerAnnouncer(Player player, String string,TextColor color) {
        component = Component.text(string)
                .color(TextColor.color(color));

        player.sendMessage(component);
    }

    public static Component componentSet(String string) {
        component = Component.text(string);
        return component;
    }

    public static Component componentSet(String string,TextColor color) {
        component = Component.text(string)
                .color(TextColor.color(color));
        return component;
    }

    public static Component nameChanger(String prefix, TextColor color) {
        component = Component.text(prefix)
                .color(color);

        return component;
    }
}
