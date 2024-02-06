package teamzesa.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.command.GodModeSet;
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


public class JoinEvent extends ComponentExchanger implements EventRegister {
    private final Announcer announcer = Announcer.getAnnouncer();
    private final UserController userController = new UserController();
    private User joinUser;
    private Player joinPlayer;
    private final PlayerJoinEvent joinEvent;

    public JoinEvent(PlayerJoinEvent event) {
        this.joinEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
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
        supplyUserKit();
        announcingJoinMsg();
        attackSpeed();
        playerFlight(); //flight Set
        checkGodMode();
        userIPCheckUp(); //접속 IP 확인
        setHealthScale();
        increaseUserJoinCnt();
    }

    private void supplyUserKit() {
        if (!ifFirstTimeJoinUser())
            return;

        for (FoodKit kit : FoodKit.values()) {
            this.joinPlayer.getInventory().addItem(kit.getFood());
        }

        for (ArmourKit kit : ArmourKit.values()) {
            ItemStack armour = kit.getArmour();
            armour.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            armour.addEnchantment(Enchantment.DURABILITY, 2);
            this.joinPlayer.getInventory().addItem(kit.getArmour());
        }

        for (ToolKit kit : ToolKit.values()) {
            ItemStack tool = kit.getToolKit();
            if (tool.getType().equals(Material.NETHERITE_SWORD))
                tool.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            else tool.addEnchantment(Enchantment.DIG_SPEED, 2);
            tool.addEnchantment(Enchantment.DURABILITY, 2);
            this.joinPlayer.getInventory().addItem(tool);
        }
    }

    private void announcingJoinMsg() {
        this.announcer.joinAnnouncer(this.joinPlayer);
        this.announcer.countAnnouncer(this.joinPlayer);

        if (this.joinUser.killStatus() == 0)
            this.joinEvent.joinMessage(
                    componentExchanger("+ " + this.joinUser.name(), ColorList.RED)
            );

        else this.joinEvent.joinMessage(
                componentExchanger("+ " + this.joinUser.name() + " " + joinUser.killStatus() + "KILL", ColorList.RED)
        );
    }

    private void attackSpeed() {
        this.joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    private void playerFlight() {
        this.joinPlayer.setAllowFlight(true);
        playerSendMsgComponentExchanger(this.joinPlayer, "플라이 활성화", ColorList.YELLOW);
    }

    private void checkGodMode() {
        new GodModeSet().setGodEffect(this.joinPlayer, this.joinUser);
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

    private void increaseUserJoinCnt() {
        this.joinUser = new UserBuilder(this.joinUser)
                .joinCount(this.joinUser.joinCount() + 1)
                .buildAndUpdate();
    }

    private boolean ifFirstTimeJoinUser() {
        return this.joinUser.joinCount() == 0;
    }
}