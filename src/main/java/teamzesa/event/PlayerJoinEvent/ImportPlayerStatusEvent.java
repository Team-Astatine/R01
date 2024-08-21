package teamzesa.event.PlayerJoinEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.userHandler.UserBuilder;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.command.AdminCommand.God;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Announcer;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Kit.FoodKit;
import teamzesa.util.Enum.Kit.ToolKit;
import teamzesa.util.Enum.WorldName;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.RanNumGenerator;

import java.util.Optional;

public class ImportPlayerStatusEvent extends StringComponentExchanger implements EventRegister {

    private User user;
    private UserKillStatus userKillStatus;
    private Player player;
    private Announcer announcer;
    private final PlayerJoinEvent event;

    public ImportPlayerStatusEvent(PlayerJoinEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.announcer = Announcer.getAnnouncer();
        this.player = this.event.getPlayer();
        this.user = new UserController().readUser(this.player.getUniqueId());
        this.userKillStatus = new KillStatusController().readUser(this.player.getUniqueId());
    }

    @Override
    public void execute() {
        supplyUserKit();
        checkingUserStatusGod();
        checkingUserStatusAttackSpeed();
        checkingUserStatusHealth();
        increaseUserJoinCnt();
        announcingJoinMsg();
        randomTeleportFirstJoinPlayer();
    }

    private void randomTeleportFirstJoinPlayer() {
        if (this.player.hasPlayedBefore())
            return;

        World world = Bukkit.getWorld(WorldName.world.getExchangeEnglish());
        int[] position = new RanNumGenerator().groundChecker(world);

        int x = position[0];
        int y = position[1];
        int z = position[2];

        this.player.teleportAsync(new Location(world, x, y, z));
        Bukkit.getLogger().info(String.format("%s Player Teleport Position is > %d %d %d", player.getName(), x, y, z));
    }

    private void checkingUserStatusGod() {
        new God().setPotionEffect(this.player, this.user);
    }

    private void checkingUserStatusAttackSpeed() {
        Optional.ofNullable(this.player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).ifPresent(
                attackSpeed -> attackSpeed.setBaseValue(40.0)
        );
    }

    private void checkingUserStatusHealth() {
        this.player.setHealthScale(this.userKillStatus.healthScale());
        this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.userKillStatus.healthScale());
    }

    private void supplyUserKit() {
        if (this.player.hasPlayedBefore())
            return;

        for (FoodKit kit : FoodKit.values()) {
            this.player.getInventory().addItem(kit.getFood());
        }

        /* Non Using Netherite Kit
        for (ArmourKit kit : ArmourKit.values()) {
            ItemStack armour = kit.getArmour();
            armour.addEnchantment(Enchantment.PROTECTION, 2);
            armour.addEnchantment(Enchantment.UNBREAKING, 2);
            this.player.getInventory().addItem(kit.getArmour());
        }
        */

        for (ToolKit kit : ToolKit.values()) {
            ItemStack tool = kit.getToolKit();
            tool.setAmount(kit.getItemCount());
            this.player.getInventory().addItem(tool);
        }
    }

    private void announcingJoinMsg() {
        this.announcer.joinAnnouncer(this.player);
        this.announcer.countAnnouncer(this.player);

        if (this.userKillStatus.killCount() == 0)
            this.event.joinMessage(
                    componentExchanger(" + " + this.user.nameList().getLast(), ColorMap.RED)
            );

        else this.event.joinMessage(
                componentExchanger(" + [ " + this.userKillStatus.killCount() + "KILL ] " + user.nameList().getLast(), ColorMap.RED)
        );
    }

    private void increaseUserJoinCnt() {
        this.user = new UserBuilder(this.user)
                .joinCount(this.user.joinCount() + 1)
                .buildAndUpdate();
    }
}
