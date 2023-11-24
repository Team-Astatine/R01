package teamzesa.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Enum.ColorList;

public abstract class ComponentExchanger {

    public @NotNull Component createLinkComponentExchanger(String text, String url, @NotNull ColorList color) {
        TextComponent.Builder component = Component.text()
                .content(text)
                .color(color.getTextColor())
                .clickEvent(ClickEvent.openUrl(url));
        return component.build();
    }

    public void sendAnnouncerComponentExchanger(String comment, @NotNull ColorList color) {
        Component component = Component.text(comment)
                .color(color.getTextColor());
        Bukkit.broadcast(component);
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, String comment) {
        player.sendMessage(Component.text(comment));
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, String comment, @NotNull ColorList color) {
        Component component = Component.text(comment)
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, @NotNull StringBuilder comment, @NotNull ColorList color) {
        Component component = Component.text(comment.toString())
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public void playerSendMsgComponentExchanger(@NotNull CommandSender sender, @NotNull String comment, @NotNull ColorList color) {
        Player player = (Player) sender;
        Component component = Component.text(comment)
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    public void playerSendMsgComponentExchanger(@NotNull CommandSender sender, @NotNull StringBuilder comment, @NotNull ColorList color) {
        Player player = (Player) sender;
        Component component = Component.text(comment.toString())
                .color(color.getTextColor());
        player.sendMessage(component);
    }

    @Contract(value = "_ -> new", pure = true)
    public @NotNull Component componentExchanger(String comment) {
        return Component.text(comment);
    }

    public @NotNull Component componentExchanger(String comment, @NotNull ColorList color) {
        return Component.text(comment)
                .color(color.getTextColor());
    }
}
