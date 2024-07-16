package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.*;

public class Moderator extends CommandRegisterSection {
    private final Set<UUID> moderatorName;

    public Moderator() {
        super(CommandExecutorMap.MODERATOR);
        this.moderatorName = new HashSet<>();
        moderList();
    }

    private void moderList() {
        UUID jaxple =     UUID.fromString("27d84b4f-5991-4001-89d5-0fdfd3374a3d");
        UUID kelriex =    UUID.fromString("7e57dd28-bdba-4312-84ea-2da58cd6e598");
        UUID gunbunjule = UUID.fromString("581a57af-91c9-4cf1-a173-85a2b48b68a7");

        this.moderatorName.add(jaxple);
        this.moderatorName.add(kelriex);
        this.moderatorName.add(gunbunjule);//18_70015401
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String successComment = "지금부터 관리자 입니다.";

        Optional.ofNullable(Bukkit.getPlayer(strings[0])).ifPresent(
                player -> {
                    if (checkUPModerator(player)) {
                        playerSendMsgComponentExchanger(player, successComment, ColorMap.ORANGE);
                        player.setOp(true);
                    }
                }
        );
        return new ArrayList<>(List.of("moderator"));
    }

    private boolean checkUPModerator(@NotNull Player sendPlayer) {
        UUID senderUUID = sendPlayer.getUniqueId(); //String 대쉬 표기
        return this.moderatorName.stream().anyMatch(senderUUID::equals);
    }
}
