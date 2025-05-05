package teamzesa.command.UserCommand.Announce;

import net.kyori.adventure.title.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.CommandRegisterSection;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Enumeration.Command.ListOfCommand;

public class TitleAstatine extends CommandRegisterSection {

    public TitleAstatine() {
        super(ListOfCommand.ASTN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;
        Title title = Title.title(
                componentExchanger("최신버전 무정부 플라이 생야생", ColorType.DISCORD_COLOR),
                componentExchanger("Astn.kr", ColorType.ORANGE)
        );
        player.showTitle(title);
        return true;
    }
}