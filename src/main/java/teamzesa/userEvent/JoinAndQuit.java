package teamzesa.userEvent;

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
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.worldSet.Announcer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JoinAndQuit implements Listener {
    private final UserMapHandler userMapHandler;
    private final Announcer announcer;

    private Player joinPlayer;
    private UserHandler userHandler;
//    private Player quitPlayer;

    public JoinAndQuit() {
        this.announcer = Announcer.getAnnouncer();
        this.userMapHandler = UserMapHandler.getUserMapHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(@NotNull PlayerJoinEvent event) {

        init(event);
        addUserData(); //User Object 추가
        addUserJoinCount(); //접속횟수
        userIPCheckUp(); //접속 IP 확인
        supplyUserKit();

        this.announcer.playerTab(this.joinPlayer);
        this.announcer.joinAnnouncer(this.joinPlayer);
        this.announcer.countAnnouncer(this.joinPlayer);

        attackSpeed();
        playerFlight(); //flight Set
        setHealthScale();
    }

    private void init(@NotNull PlayerJoinEvent e) {
        this.joinPlayer = e.getPlayer();
        this.userHandler = new UserHandler(this.joinPlayer);
    }

    private void playerFlight() {
        this.joinPlayer.setAllowFlight(true);
        ComponentExchanger.playerAnnouncer(this.joinPlayer,"플라이 활성화",ColorList.YELLOW);
    }

    private void addUserData() {
        UserMapHandler userData = UserMapHandler.getUserMapHandler();
        if (Optional.ofNullable(userData.getUser(this.joinPlayer)).isEmpty())
            userMapHandler.addUser(this.joinPlayer);
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
        List<ItemStack> kit = new ArrayList<>();
        ItemStack fork = new ItemStack(Material.COOKED_BEEF, 20);
        ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 2);

        kit.add(fork);
        kit.add(apple);

        ItemStack netheriteHat = new ItemStack(Material.NETHERITE_HELMET);
        netheriteHat.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        netheriteHat.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteHat);

        ItemStack netheriteChestPlate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        netheriteChestPlate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        netheriteChestPlate.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteChestPlate);

        ItemStack netheriteLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);
        netheriteLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        netheriteLeggings.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteLeggings);

        ItemStack netheriteBoots = new ItemStack(Material.NETHERITE_BOOTS);
        netheriteBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        netheriteBoots.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteBoots);

        ItemStack netheriteSword = new ItemStack(Material.NETHERITE_SWORD);
        netheriteSword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        netheriteSword.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteSword);

        ItemStack netheritePickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        netheritePickaxe.addEnchantment(Enchantment.DIG_SPEED, 2);
        netheritePickaxe.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheritePickaxe);

        ItemStack netheriteAxe = new ItemStack(Material.NETHERITE_AXE);
        netheriteAxe.addEnchantment(Enchantment.DIG_SPEED, 2);
        netheriteAxe.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteAxe);

        ItemStack netheriteShovel = new ItemStack(Material.NETHERITE_SHOVEL);
        netheriteShovel.addEnchantment(Enchantment.DIG_SPEED, 2);
        netheriteShovel.addEnchantment(Enchantment.DURABILITY, 2);
        kit.add(netheriteShovel);

        for (ItemStack newTool : kit)
            this.joinPlayer.getInventory().addItem(newTool);
    }

    private void userIPCheckUp() {
        String message = newSubscribers() ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";

        if (newSubscribers())
            ComponentExchanger.playerAnnouncer(this.joinPlayer, message, ColorList.YELLOW);

        if (this.userHandler.existsNotIP(this.joinPlayer.getAddress())) {
            this.userHandler.addIP(this.joinPlayer.getAddress());
            ComponentExchanger.playerAnnouncer(this.joinPlayer, message, ColorList.YELLOW);
        }
    }

    private boolean newSubscribers() {
        int joinCnt =  this.userHandler.getUser().getJoinCount();
        return joinCnt == 1;
    }

    private void addUserJoinCount() {
        this.userHandler.addJoinCnt();
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
