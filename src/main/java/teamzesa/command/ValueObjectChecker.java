package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValueObjectChecker extends CommandRegisterSection {

    public ValueObjectChecker() {
        super(CommandExecutorMap.USER_OBJECT_CHECKER);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        User user = null;
        try {
            user = new UserController().readUser(strings[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user)
                .ifPresentOrElse(
                        existUser -> {
                            UserKillStatus userKillStatus = new KillStatusController().readUser(existUser.uuid());
                            sendComment(commandSender, existUser + "\n\n" + userKillStatus);
                        },

                        () -> {
                            sendComment(commandSender, "존재하지 않는 유저");
                        }
                );

        return new ArrayList<>(List.of("vo"));
    }

    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            playerSendMsgComponentExchanger(sender, comment, ColorMap.YELLOW);
        else Bukkit.getLogger().info(comment);
    }
}
