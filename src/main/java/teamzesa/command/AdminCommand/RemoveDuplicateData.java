package teamzesa.command.AdminCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.RObjectIOHandler;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.DataFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RemoveDuplicateData extends CommandRegisterSection {
    private User senderUser;
    private Player senderPlayer;
    private boolean isConsoleSend = false;

    public RemoveDuplicateData() {
        super(CommandExecutorMap.REMOVE_DUPLICATE_USER_DATA);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            this.senderUser = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.isConsoleSend = true
        );

        if (this.senderUser != null && !this.senderPlayer.isOp() && !this.isConsoleSend) {
            playerSendMsgComponentExchanger(this.senderPlayer, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
            return false;
        }

        ArrayList<User> newUsers = new ArrayList<>();
        UserController userController = new UserController();

        ArrayList<User> allUserTable = userController.getAllUserTable();
        for (User user : allUserTable) {
            Set<String> userName = new HashSet<>(user.nameList());
            ArrayList<String> nonDupeUserName = new ArrayList<>(userName);

            newUsers.add(new User(
                    user.uuid(),
                    nonDupeUserName,
                    user.connectionIPList(),
                    user.joinCount(),
                    user.level(),
                    user.isGodMode(),
                    user.isAnnouncing()
            ));
        }

        userController.updateAllUserData(newUsers);
        playerSendMsgComponentExchanger(this.senderPlayer, "성공적으로 중복이 제거되었습니다.", ColorMap.YELLOW);

        new RObjectIOHandler().exportData(
                DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName());
        playerSendMsgComponentExchanger(this.senderPlayer, "성공적으로 데이터가 저장되었습니다.", ColorMap.YELLOW);
        return true;
    }
}
