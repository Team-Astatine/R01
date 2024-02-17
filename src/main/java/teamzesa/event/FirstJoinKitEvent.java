package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ArmourKit;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.FoodKit;
import teamzesa.util.Enum.ToolKit;
import teamzesa.util.IOHandler.Announcer;
import teamzesa.util.userHandler.UserBuilder;
import teamzesa.util.userHandler.UserController;

public class FirstJoinKitEvent extends ComponentExchanger implements EventRegister {

    private int playerJoinCnt;
    private User user;
    private Player player;
    private Announcer announcer;
    private final PlayerJoinEvent event;

    public FirstJoinKitEvent(PlayerJoinEvent event) {
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
        supplyKit();
        announcingJoinMsg();
        increaseUserJoinCnt();
    }

    private void supplyKit() {
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

        if (this.user.killStatus() == 0)
            this.event.joinMessage(
                    componentExchanger("+ " + this.user.name(), ColorList.RED)
            );

        else this.event.joinMessage(
                componentExchanger("+ " + this.user.name() + " " + user.killStatus() + "KILL", ColorList.RED)
        );
    }

    private void increaseUserJoinCnt() {
        this.user = new UserBuilder(this.user)
                .joinCount(this.user.joinCount() + 1)
                .buildAndUpdate();
    }
}
