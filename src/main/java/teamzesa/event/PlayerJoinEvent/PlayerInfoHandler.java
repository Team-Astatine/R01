package teamzesa.event.PlayerJoinEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;

import java.util.Optional;


public class PlayerInfoHandler extends StringComponentExchanger implements EventRegister {
    private User joinUser;
    private Player joinPlayer;
    private UserController userController;
    private KillStatusController userKillStatus;
    private final PlayerJoinEvent joinEvent;

    public PlayerInfoHandler(PlayerJoinEvent event) {
        this.joinEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.userController = new UserController();
        this.userKillStatus = new KillStatusController();
        this.joinPlayer = this.joinEvent.getPlayer();
        User user = this.userController.readUser(this.joinPlayer.getUniqueId());

        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> {
                    this.joinUser = new UserBuilder(existUser)
                            .level(this.joinPlayer.getLevel())
                            .buildAndUpdate();
                },

                () -> {
                    this.userController.createUser(this.joinPlayer);
                    this.userKillStatus.createUserKillStatus(this.joinPlayer);
                    this.joinUser = this.userController.readUser(this.joinPlayer.getUniqueId());
                    Bukkit.getLogger().info(this.joinPlayer.getName() + "님이 신규유저 등록됐습니다.");
                });
    }

    @Override
    public void execute() {
        String name = this.joinPlayer.getName();
        String ip = this.joinPlayer.getAddress().getHostName();

        updateUserInfo(name, ip, this.joinPlayer.hasPlayedBefore());
    }

    private void updateUserInfo(String name, String ip, boolean hasPlayedBefore) {
        boolean equalsLastName = this.joinUser.nameList().getLast().equals(name);
        boolean existsIP = this.joinUser.connectionIPList().contains(ip);

        if (!equalsLastName) {
            updateNameList(name);
            sendMessageToPlayer(this.joinPlayer, "새로운 이름으로 접속하셨습니다.", "신규 이름을 등록합니다.", hasPlayedBefore);
        }

        if (!existsIP) {
            updateIPList(ip);
            sendMessageToPlayer(this.joinPlayer, "새로운 IP로 접속하셨습니다.", "신규 IP를 등록합니다.", hasPlayedBefore);
        }
    }

    private void updateNameList(String name) {
        this.joinUser = new UserBuilder(this.joinUser)
                .nameList(name)
                .buildAndUpdate();
    }

    private void updateIPList(String ip) {
        this.joinUser = new UserBuilder(this.joinUser)
                .ipList(ip)
                .buildAndUpdate();
    }

    private void sendMessageToPlayer(Player player, String existingMessage, String newMessage, boolean hasPlayedBefore) {
        String message = hasPlayedBefore ? existingMessage : newMessage;
        playerSendMsgComponentExchanger(player, message, ColorMap.YELLOW);
    }

}