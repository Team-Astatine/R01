package teamzesa.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.userHandler.UserController;

public class EnhanceItemDmgEvent implements EventRegister, Listener {
    private Player damager;
    private User damagerUser;
    private ItemStack hurtWeapon;

    private Entity targetEntity;
    private final EntityDamageByEntityEvent event;

    public EnhanceItemDmgEvent(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        if (this.event.getDamager() instanceof Player player) {
            this.damager = player;
            this.damagerUser = new UserController().readUser(player.getUniqueId());
            this.hurtWeapon = player.getInventory().getItemInMainHand();
        }
        else this.event.setCancelled(true);

        this.targetEntity = this.event.getEntity();
    }

    @Override
    public void execute() {
//   System.out.println(this.damager);
//   System.out.println(this.damagerUser);
//   System.out.println(this.hurtWeapon);
//   System.out.println(this.targetEntity);
//   CraftPlayer{name=JAXPLE}
//   User[uuid = 27d84b4f-5991-4001-89d5-0fdfd3374a3d, name=JAXPLE, connectionIPList=[localhost, 127.0.0.1], joinCount=12, level=11, healthScale=20.0, killCount=0, isGodMode=false, isAnnouncing=false]
//   ItemStack{VILLAGER_SPAWN_EGG x 64}
//   CraftVillager
    }

    @Override
    public void entityDmg(EntityDamageEvent event) {
//        this.event.getEntity().
    }
}
