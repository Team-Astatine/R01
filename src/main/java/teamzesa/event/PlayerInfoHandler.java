package teamzesa.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.entity.User;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;

import java.util.HashSet;
import java.util.Optional;


public class PlayerInfoHandler extends StringComponentExchanger implements EventRegister {
    private User joinUser;
    private Player joinPlayer;
    private  UserController userController;
    private final PlayerJoinEvent joinEvent;

    public PlayerInfoHandler(PlayerJoinEvent event) {
        this.joinEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.userController = new UserController();
        this.joinPlayer = this.joinEvent.getPlayer();
        User user = this.userController.readUser(this.joinPlayer);

        Optional.ofNullable(user).ifPresentOrElse(
            existUser -> {
                this.joinUser = new UserBuilder(existUser)
                        .level(this.joinPlayer.getLevel())
                        .buildAndUpdate();
            },

            () -> {
                this.userController.createUser(this.joinPlayer);
                this.joinUser = this.userController.readUser(this.joinPlayer);
                Bukkit.getLogger().info(this.joinUser.name() + "님이 신규유저 등록됐습니다.");
            });
    }

    @Override
    public void execute() {
        String ip = this.joinPlayer.getAddress().getHostName();
        String message = ifFirstTimeJoinUser() ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";
        boolean nonExistsIP = this.joinUser.connectionIPList().stream()
                .noneMatch(ipAddress -> ipAddress.equals(ip));

        if (ifFirstTimeJoinUser() || nonExistsIP) {
            HashSet<String> ipList = this.joinUser.connectionIPList();
            ipList.add(ip);

            this.joinUser = new UserBuilder(this.joinUser)
                    .ipList(ipList)
                    .buildAndUpdate();

            playerSendMsgComponentExchanger(this.joinPlayer, message, ColorMap.YELLOW);
        }
    }

    private boolean ifFirstTimeJoinUser() {
        return this.joinUser.joinCount() == 0;
    }
}