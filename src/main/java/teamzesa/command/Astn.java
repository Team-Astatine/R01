package teamzesa.command;

import net.kyori.adventure.title.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandExecutorMap;

public class Astn extends CommandRegisterSection {

    public Astn() {
        super(CommandExecutorMap.ASTN);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        Title title = Title.title(
                componentExchanger("최신버전 무정부 플라이 생야생", ColorList.DISCORD_COLOR),
                componentExchanger("Astn.kr", ColorList.ORANGE)
        );
        player.showTitle(title);
        return true;
    }
}