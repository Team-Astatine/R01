package teamzesa.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Enum.ColorList;

public record Astn() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;
        Title title = Title.title(
                Component.text("최신버전 무정부 플라이 생야생", ColorList.DISCORD_COLOR.getTextColor()),
                Component.text("Astn.kr", ColorList.ORANGE.getTextColor())
        );
        player.showTitle(title);
        return true;
    }
}
