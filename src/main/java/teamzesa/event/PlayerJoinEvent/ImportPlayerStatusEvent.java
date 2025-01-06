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
import teamzesa.DataBase.UserHandler.UserBuilder;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.command.ModeratorCommand.SetGodMode;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Announcer;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.Kit.FoodKit;
import teamzesa.Enum.Kit.ToolKit;
import teamzesa.Enum.WorldName;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.RanNumGenerator;

import java.util.Arrays;
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

        World world = Bukkit.getWorld(WorldName.WORLD.getExchangeEnglish());
        int[] position = new RanNumGenerator().getRandomPosition(world);

        int x = position[0];
        int y = position[1];
        int z = position[2];

        this.player.teleportAsync(new Location(world, x, y, z));
        Bukkit.getLogger().info(String.format("%s Player Teleport Position is > %d %d %d", player.getName(), x, y, z));
    }

    private void checkingUserStatusGod() {
        new SetGodMode().setPotionEffect(this.player, this.user);
    }

    private void checkingUserStatusAttackSpeed() {
        Optional.ofNullable(this.player.getAttribute(Attribute.ATTACK_SPEED)).ifPresent(
                attackSpeed -> attackSpeed.setBaseValue(40.0)
        );
    }

    private void checkingUserStatusHealth() {
        this.player.setHealthScale(this.userKillStatus.healthScale());
        this.player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(this.userKillStatus.healthScale());
    }

    private void supplyUserKit() {
        if (this.player.hasPlayedBefore())
            return;

        Arrays.stream(FoodKit.values()).forEach(i ->
                this.player.getInventory().addItem(i.getFood())
        );

        /* Non Using Netherite Kit
        for (ArmourKit kit : ArmourKit.values()) {
            ItemStack armour = kit.getArmour();
            armour.addEnchantment(Enchantment.PROTECTION, 2);
            armour.addEnchantment(Enchantment.UNBREAKING, 2);
            this.player.getInventory().addItem(kit.getArmour());
        }
        */

        Arrays.stream(ToolKit.values()).forEach(i -> {
            ItemStack tool = i.getToolKit();
            tool.setAmount(i.getSupplyAmount());
            this.player.getInventory().addItem(tool);
        });
    }

    private void announcingJoinMsg() {
        this.player.performCommand("help");
        this.announcer.countAnnouncer(this.player);

        if (this.userKillStatus.killCount() == 0)
            this.event.joinMessage(
                    componentExchanger(" + " + this.user.nameList().getLast(), ColorList.RED)
            );

        else this.event.joinMessage(
                componentExchanger(" + [ " + this.userKillStatus.killCount() + "KILL ] " + user.nameList().getLast(), ColorList.RED)
        );
    }

    private void increaseUserJoinCnt() {
        this.user = new UserBuilder(this.user)
                .joinCount(this.user.joinCount() + 1)
                .buildAndUpdate();
    }
}
