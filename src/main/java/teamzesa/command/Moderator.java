package teamzesa.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.*;

public record Moderator() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Set<UUID> moderatorName = new HashSet<>(Arrays.asList(
                UUID.fromString("27d84b4f-5991-4001-89d5-0fdfd3374a3d"),
                UUID.fromString("7e57dd28-bdba-4312-84ea-2da58cd6e598"),
                UUID.fromString("581a57af-91c9-4cf1-a173-85a2b48b68a7")
        ));

        Optional.of((Player) sender).ifPresent(
                player -> {
                    if (checkUPModerator(player, moderatorName)) {
                        String successComment = "지금부터 관리자 입니다.";
                        player.sendMessage(Component.text(successComment, ColorList.ORANGE.getTextColor()));
                        player.setOp(true);
                    } else {
                        String FailComment = "사용권한이 없습니다.";
                        player.sendMessage(Component.text(FailComment, ColorList.RED.getTextColor()));
                    }
                }
        );
        return true;
    }

    private boolean checkUPModerator(@NotNull Player sendPlayer, Set<UUID> moderatorName) {
        UUID senderUUID = sendPlayer.getUniqueId(); //String 대쉬 표기
        return moderatorName.stream().anyMatch(senderUUID::equals);
    }
}
