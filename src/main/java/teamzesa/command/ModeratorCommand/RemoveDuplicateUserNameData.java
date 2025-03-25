package teamzesa.command.ModeratorCommand;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.RObjectIOHandler;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.ListOfCommand;
import teamzesa.Enum.DataFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RemoveDuplicateUserNameData extends CommandRegisterSection {
    private User senderUser;
    private Player senderPlayer;
    private boolean consoleSend = false;

    public RemoveDuplicateUserNameData() {
        super(ListOfCommand.REMOVE_DUPLICATE_USER_DATA);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        try {
            this.senderUser = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        if (ObjectUtils.allNotNull(this.senderUser) && !this.senderPlayer.isOp() || !this.consoleSend) {
            playerSendMsgComponentExchanger(this.senderPlayer, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorList.RED);
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
                    user.godMode(),
                    user.announcingSkip()
            ));
        }

        userController.updateAllUserData(newUsers);
        playerSendMsgComponentExchanger(this.senderPlayer, "성공적으로 중복이 제거되었습니다.", ColorList.YELLOW);

        new RObjectIOHandler().exportData(
                DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName());
        playerSendMsgComponentExchanger(this.senderPlayer, "성공적으로 데이터가 저장되었습니다.", ColorList.YELLOW);
        return true;
    }
}
