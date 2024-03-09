package teamzesa.util.Interface;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.ConfigMenu;

public abstract class ComponentExchanger {
    private Component exchangerStringToComponent(String comment) {
        return Component.text(comment);
    }

    private Component exchangerStringToComponentAndColor(String comment, ColorList color) {
        return Component.text()
                .content(comment)
                .color(color.getTextColor())
                .build();
    }

    public @NotNull Component createLinkComponentExchanger(String comment, String url, @NotNull ColorList color) {
        return Component.text()
                .content(comment)
                .color(color.getTextColor())
                .clickEvent(ClickEvent.openUrl(url))
                .build();
    }

    public void sendAnnouncerComponentExchanger(String comment, @NotNull ColorList color) {
        Bukkit.broadcast(exchangerStringToComponentAndColor(comment,color));
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, String comment) {
        player.sendMessage(exchangerStringToComponent(comment));
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, String comment, @NotNull ColorList color) {
        player.sendMessage(exchangerStringToComponentAndColor(comment,color));
    }

    public void playerSendMsgComponentExchanger(@NotNull CommandSender sender, @NotNull String comment, @NotNull ColorList color) {
        playerSendMsgComponentExchanger((Player) sender,comment,color);
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, @NotNull StringBuilder comment, @NotNull ColorList color) {
        playerSendMsgComponentExchanger(player,comment.toString(),color);
    }

    public void playerSendMsgComponentExchanger(@NotNull CommandSender sender, @NotNull StringBuilder comment, @NotNull ColorList color) {
        playerSendMsgComponentExchanger((Player) sender,comment.toString(),color);
    }

    public Component componentExchanger(Object comment) {
        if (comment instanceof String stringMsg)
            return exchangerStringToComponent(stringMsg);
        if (comment instanceof ConfigMenu configMsg)
            return exchangerStringToComponent(configMsg.getConfigMessage());
        return null;
    }

    public @NotNull Component componentExchanger(String comment, @NotNull ColorList color) {
        return exchangerStringToComponentAndColor(comment,color);
    }
}
