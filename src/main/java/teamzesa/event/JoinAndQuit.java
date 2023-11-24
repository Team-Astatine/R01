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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.ArmourKit;
import teamzesa.util.Enum.FoodKit;
import teamzesa.util.Enum.ToolKit;
import teamzesa.dataValue.User;
import teamzesa.util.userHandler.UserUtil;
import teamzesa.util.userHandler.UserMapHandler;

import java.net.InetSocketAddress;
import java.util.Optional;


public class JoinAndQuit extends ComponentExchanger implements Listener {
    private final UserMapHandler userMapHandler;
    private final Announcer announcer;

    private Player joinPlayer;
    private UserUtil userUtil;
//    private Player quitPlayer;

    public JoinAndQuit() {
        this.announcer = Announcer.getAnnouncer();
        this.userMapHandler = UserMapHandler.getUserMapHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public synchronized void onJoin(@NotNull PlayerJoinEvent event) {

        init(event.getPlayer());
        this.userUtil.increaseJoinCnt(); //접속횟수
        userIPCheckUp(); //접속 IP 확인
        supplyUserKit();

        this.announcer.playerTab(this.joinPlayer);
        this.announcer.joinAnnouncer(this.joinPlayer);
        this.announcer.countAnnouncer(this.joinPlayer);

        attackSpeed();
        playerFlight(); //flight Set
        setHealthScale();
    }

    private void init(Player player) {
        this.joinPlayer = player;

        User user = this.userMapHandler.getUser(player);
        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> this.userUtil = new UserUtil(existUser),
                () -> {
                    this.userMapHandler.addUser(player);
                    this.userUtil = new UserUtil(player);
                }
        );
    }

    private void playerFlight() {
        this.joinPlayer.setAllowFlight(true);
        playerSendMsgComponentExchanger(this.joinPlayer,"플라이 활성화",ColorList.YELLOW);
    }

    private void supplyUserKit() {
        if (!newSubscribers())
            return;

        supplyKit();
        randomTeleport();
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

    private void userIPCheckUp() {
        InetSocketAddress ip = null;
        if (Optional.ofNullable(this.joinPlayer.getAddress()).isPresent())
            ip = this.joinPlayer.getAddress();

        String message = newSubscribers() ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";
        if (newSubscribers() || this.userUtil.nonExistsIP(ip)) {
            this.userUtil.addIP(ip);
            this.userMapHandler.updateUser(this.userUtil.getUser());
            playerSendMsgComponentExchanger(this.joinPlayer, message, ColorList.YELLOW);
        }
    }

    private boolean newSubscribers () {
        int joinCnt =  this.userUtil.getUser().getJoinCount();
        return joinCnt == 1;
    }

    private void setHealthScale() {
        User user = userMapHandler.getUser(this.joinPlayer.getUniqueId());
        this.joinPlayer.setHealthScale(user.getHealthScale());
        this.joinPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.getHealthScale());
    }

    private void attackSpeed() {
        this.joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        /*
        Player quitPlayer = e.getPlayer();
        double healthScale = quitPlayer.getHealthScale();

//        valid healthScale
        if (healthScale != 20.0)
            userMapHandler.updateUser(quitPlayer.getUniqueId(), healthScale);
        */
    }
}