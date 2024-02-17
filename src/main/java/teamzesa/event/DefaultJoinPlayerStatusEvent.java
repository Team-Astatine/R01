package teamzesa.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.command.God;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.ArmourKit;
import teamzesa.util.Enum.FoodKit;
import teamzesa.util.Enum.ToolKit;
import teamzesa.entity.User;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;

import java.util.HashSet;
import java.util.Optional;


public class DefaultJoinPlayerStatusEvent extends ComponentExchanger implements EventRegister {
    private User joinUser;
    private Player joinPlayer;
    private  UserController userController;
    private final PlayerJoinEvent joinEvent;

    public DefaultJoinPlayerStatusEvent(PlayerJoinEvent event) {
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
                }
        );
    }

    @Override
    public void execute() {
        attackSpeed();
        playerFlight(); //flight Set
        checkGodMode();
        userIPCheckUp(); //접속 IP 확인
        setHealthScale();
    }

    private void attackSpeed() {
        Optional.ofNullable(this.joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).ifPresent(
                attackSpeed -> attackSpeed.setBaseValue(40.0)
        );
    }

    private void playerFlight() {
        this.joinPlayer.setAllowFlight(true);
        playerSendMsgComponentExchanger(this.joinPlayer, "플라이 활성화", ColorList.YELLOW);
    }

    private void checkGodMode() {
        new God().setGodEffect(this.joinPlayer, this.joinUser);
    }

    private void userIPCheckUp() {
        String ip = this.joinPlayer.getAddress().getHostName();
        String message = ifFirstTimeJoinUser() ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";
        boolean nonExistsIP = this.joinUser.connectionIPList().stream().noneMatch(
                ipAddress -> ipAddress.equals(ip));

        if (ifFirstTimeJoinUser() || nonExistsIP) {
            HashSet<String> ipList = this.joinUser.connectionIPList();
            ipList.add(ip);

            this.joinUser = new UserBuilder(this.joinUser)
                    .ipList(ipList)
                    .buildAndUpdate();

            playerSendMsgComponentExchanger(this.joinPlayer, message, ColorList.YELLOW);
        }
    }

    private void setHealthScale() {
        User user = this.userController.readUser(this.joinPlayer);
        this.joinPlayer.setHealthScale(user.healthScale());
        this.joinPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.healthScale());
    }

    private boolean ifFirstTimeJoinUser() {
        return this.joinUser.joinCount() == 0;
    }
}