package teamzesa.event.Enhance.LongRange;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceLongRangeWeaponHurtEvent extends EnhanceUtil implements EventRegister {
    private Player damager;
    private ItemStack weapon;
    private Projectile projectile;
    private final EntityDamageEvent event;

    public EnhanceLongRangeWeaponHurtEvent(EntityDamageEvent event) {
        this.event = event;

        if (this.event.getDamageSource().getDirectEntity() instanceof Player player) {
            this.damager = player;
            this.weapon = player.getInventory().getItemInMainHand();
        }
        else return;

        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        if (this.damager == null)
            return;

        if (!this.weapon.hasCustomModelData())
            return;

        double projectileDamage = getProjectileDamage(this.weapon);
    }

}
