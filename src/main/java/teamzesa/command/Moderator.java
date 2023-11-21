package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.event.EventExecutor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Moderator implements CommandExecutor, EventExecutor {
    private final Set<String> moderatorName;

    public Moderator() {
        this.moderatorName = new HashSet<>();
        moderList();
    }

    private void moderList() {
        this.moderatorName.add("27d84b4f-5991-4001-89d5-0fdfd3374a3d");//jaxple
        this.moderatorName.add("7e57dd28-bdba-4312-84ea-2da58cd6e598");//18_70015401
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0){
            if (sender instanceof Player)
                ComponentExchanger.playerAnnouncer(sender,"/관리자 [닉네임]",ColorList.RED);
            else
                Bukkit.getLogger().info("[R01] /관리자 [닉네임]");
            return false;
        }

        Optional.ofNullable(Bukkit.getPlayer(args[0]))
                .ifPresent(player -> {
                    if (checkupMod(player)){
                        player.setOp(true);
                        ComponentExchanger.playerAnnouncer(player,"지금부터 관리자 입니다.", ColorList.ORANGE);
                    }
                });

        return true;
    }

    private boolean checkupMod(@NotNull Player sendPlayer) {
        String senderUUID = sendPlayer.getUniqueId().toString(); //대쉬 표기되며 출력됌
        return this.moderatorName.stream().anyMatch(senderUUID::equals);
    }
}
