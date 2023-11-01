package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;
import teamzesa.resgisterEvent.EventExecutor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Moderator implements CommandExecutor, EventExecutor {
    private final Set<Player> moderatorName;

    public Moderator() {
        this.moderatorName = new HashSet<>();
        moderList();
    }

    private void moderList() {
        this.moderatorName.add(Bukkit.getPlayer("JAXPLE"));
        this.moderatorName.add(Bukkit.getPlayer("18_70015401"));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0)
            return false;

        if (checkupMod(sender))
            return false;

        Optional<Player> targetPlayer = Optional.ofNullable(Bukkit.getPlayer(args[0]));
        targetPlayer.ifPresent(player -> {
            player.setOp(true);
            ComponentExchanger.playerAnnouncer(player,"지금부터 관리자 입니다.", ColorList.ORANGE);
        });

        return true;
    }

    private boolean checkupMod(@NotNull CommandSender sender) {
        return this.moderatorName.stream().noneMatch(sender::equals);
    }
}
