package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.GodModeSet;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.ArmourKit;
import teamzesa.util.Enum.FoodKit;
import teamzesa.util.Enum.ToolKit;
import teamzesa.entity.User;
import teamzesa.util.RanNumGenerator;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class JoinAndQuit extends ComponentExchanger implements Listener {
    private final Announcer announcer;
    private final UserController userController;

    private Player joinPlayer;
    private User user;

    public JoinAndQuit() {
        this.announcer = Announcer.getAnnouncer();
        this.userController = new UserController();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public synchronized void onJoin(@NotNull PlayerJoinEvent event) {
        this.joinPlayer = event.getPlayer();

        init();
        supplyUserKit();
        userIPCheckUp(); //접속 IP 확인
        increaseUserJoinCnt();

        announcingJoinMsg();

        attackSpeed();
        playerFlight(); //flight Set
        setHealthScale();
        checkGodMode();
    }

    private void increaseUserJoinCnt() {
        this.user = new UserBuilder(this.user)
                .joinCount(this.user.joinCount() + 1)
                .build();
        this.userController.updateUser(this.user);
    }

    private void checkGodMode() {
        new GodModeSet().setGodEffect(this.joinPlayer,this.user);
    }

    private void announcingJoinMsg() {
//        this.announcer.setPlayerTabHeader(this.joinPlayer);
        this.announcer.joinAnnouncer(this.joinPlayer);
        this.announcer.countAnnouncer(this.joinPlayer);
        this.announcer.joinKillStatusAnnouncer(this.user);
    }

    private void init() {

        Optional.ofNullable(this.userController.readUser(this.joinPlayer)).ifPresentOrElse(
                existUser -> this.user = existUser,
                ()        -> {
                    this.userController.createUser(this.joinPlayer);
                    this.user = this.userController.readUser(this.joinPlayer);
                    System.out.println("init user > " + this.user);
                }
        );
    }

    private void playerFlight() {
        this.joinPlayer.setAllowFlight(true);
        playerSendMsgComponentExchanger(this.joinPlayer,"플라이 활성화",ColorList.YELLOW);
    }

    private void userIPCheckUp() {
        InetSocketAddress ip = null;
        if (Optional.ofNullable(this.joinPlayer.getAddress()).isPresent())
            ip = this.joinPlayer.getAddress();

        String message = newSubscribers() ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";
        if (newSubscribers() || nonExistsIP()) {
            HashSet<String> ipList = this.user.ipList();
            ipList.add(ip.getHostName());

            this.userController.updateUser(new UserBuilder(this.user)
                    .ipList(ipList)
                    .build());

            playerSendMsgComponentExchanger(this.joinPlayer, message, ColorList.YELLOW);
        }
    }

    private boolean nonExistsIP() {
        String accessIP = this.joinPlayer.getAddress().getHostName();
        return this.user.ipList().stream().noneMatch(
                ip -> ip.equals(accessIP));
    }

    private void supplyUserKit() {
        if (!newSubscribers())
            return;

        supplyKit();
//        스폰범위 변경으로 해결
//        randomTeleport();
    }

    private void randomTeleport() {
        int x = RanNumGenerator.numGenerator();
        int z = RanNumGenerator.numGenerator();
        int y = RanNumGenerator.groundChecker(this.joinPlayer.getWorld());
        this.joinPlayer.teleport(new Location(this.joinPlayer.getWorld(),x,y,z));
    }

    private void supplyKit() {
        for (FoodKit kit : FoodKit.values()){
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

    private boolean newSubscribers () {
        return this.user.joinCount() == 0;
    }

    private void setHealthScale() {
        User user = this.userController.readUser(this.joinPlayer);
        this.joinPlayer.setHealthScale(user.healthScale());
        this.joinPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.healthScale());
    }

    private void attackSpeed() {
        this.joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }
}