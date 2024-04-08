package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.command.God;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ArmourKit;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.FoodKit;
import teamzesa.util.Enum.ToolKit;
import teamzesa.util.IOHandler.Announcer;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;

import java.util.Optional;

public class ImportPlayerStatusEvent extends StringComponentExchanger implements EventRegister {

    private int playerJoinCnt;
    private User user;
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
        this.user = new UserController().readUser(this.player);
        this.playerJoinCnt = this.user.joinCount();
    }

    @Override
    public void execute() {
        supplyUserKit();
        checkingUserStatusGod();
        checkingUserStatusAttackSpeed();
        checkingUserStatusHealth();
        increaseUserJoinCnt();
        announcingJoinMsg();
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
        this.player.setHealthScale(this.user.healthScale());
        this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.user.healthScale());
    }

    private void supplyUserKit() {
        if (this.playerJoinCnt > 1) return;

        for (FoodKit kit : FoodKit.values()) {
            this.player.getInventory().addItem(kit.getFood());
        }

        for (ArmourKit kit : ArmourKit.values()) {
            ItemStack armour = kit.getArmour();
            armour.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            armour.addEnchantment(Enchantment.DURABILITY, 2);
//            armour.setCustomModelData(1);
            this.player.getInventory().addItem(kit.getArmour());
        }

        for (ToolKit kit : ToolKit.values()) {
            ItemStack tool = kit.getToolKit();
            if (tool.getType().equals(Material.NETHERITE_SWORD))
                tool.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            else tool.addEnchantment(Enchantment.DIG_SPEED, 2);
            tool.addEnchantment(Enchantment.DURABILITY, 2);
            this.player.getInventory().addItem(tool);
        }
    }
    private void announcingJoinMsg() {
        this.announcer.joinAnnouncer(this.player);
        this.announcer.countAnnouncer(this.player);

        if (this.user.killCount() == 0)
            this.event.joinMessage(
                componentExchanger(" + " + this.user.name(), ColorList.RED)
            );

        else this.event.joinMessage(
            componentExchanger(" + [ " + user.killCount() + "KILL ] " + user.name(), ColorList.RED)
        );
    }

    private void increaseUserJoinCnt() {
        this.user = new UserBuilder(this.user)
                .joinCount(this.user.joinCount() + 1)
                .buildAndUpdate();
    }
}
