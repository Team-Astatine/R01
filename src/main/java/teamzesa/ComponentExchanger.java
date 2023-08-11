package teamzesa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;

public abstract class ComponentExchanger {

    private static Component component;

    private TextColor colorExchanger(Color color) {
//        Ensure 24-bit RGB value
        int rgb = color.getRGB() & 0xFFFFFF;
        return TextColor.color(rgb);
    }

    public Component createLinkComponent(String text, String url, Color color) {
        TextComponent.Builder component = Component.text()
                .content(text)
                .color(colorExchanger(color))
                .clickEvent(ClickEvent.openUrl(url));
        return component.build();
    }

    public void serverAnnouncer(String string, Color color) {
        component = Component.text(string)
                .color(colorExchanger(color));
        Bukkit.broadcast(component);
    }

    public void playerAnnouncer(Player player, String string, Color color) {
        component = Component.text(string)
                .color(colorExchanger(color));
        player.sendMessage(component);
    }

    public void playerAnnouncer(Player player, String string) {
        component = Component.text(string);
        player.sendMessage(component);
    }

    public Component componentSet(String string) {
        component = Component.text(string);
        return component;
    }

    public Component componentSet(String string, Color color) {
        component = Component.text(string)
                .color(colorExchanger(color));
        return component;
    }
}
