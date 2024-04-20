package teamzesa.event.Enhance;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
import teamzesa.util.userHandler.UserController;

public class EnhanceItemDmgEvent implements EventRegister {
    private static final float ENHANCE_INCREASE_DAMAGE_PERCENTAGE = 10;

    private Entity targetEntity;
    private Player damager;
    private ItemStack weapon;
    private final EntityDamageEvent event;

    public EnhanceItemDmgEvent(EntityDamageEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        if (this.event.getDamageSource().getDirectEntity() instanceof Player player) {
            this.damager = player;
            this.weapon = player.getInventory().getItemInMainHand();
        }
        else this.event.setCancelled(true);

        this.targetEntity = this.event.getEntity();
    }

    @Override
    public void execute() {
        if (this.damager == null)
            return;

//        debug
//        float currentWeaponDamage = this.weapon.
//        float attackDamage = attackDamageCalculator();

    }

    public float attackDamageCalculator() {
        float cnt = Float.parseFloat(String.valueOf(this.weapon.getCustomModelData()));
        System.out.println("cnt > " + cnt);
        return ENHANCE_INCREASE_DAMAGE_PERCENTAGE + cnt;
    }
}
