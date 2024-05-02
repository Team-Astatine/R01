package teamzesa.event.Enhance.LongRange;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceLongRangeWeaponHurtEvent extends EnhanceUtil implements EventRegister {
    private DamageSource damageSource;
    private final EntityDamageEvent event;

    public EnhanceLongRangeWeaponHurtEvent(EntityDamageEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.damageSource = this.event.getDamageSource();
    }

    @Override
    public void execute() {
        if (!(this.damageSource.getDirectEntity() instanceof Projectile projectile))
            return;

        if (!(this.damageSource.getCausingEntity() instanceof Player player))
            return;

        ItemStack weapon = player.getInventory().getItemInMainHand();
        if (!weapon.hasCustomModelData())
            return;

        double projectileDamage = getProjectileDamage(weapon);

        System.out.println(projectileDamage);
        System.out.println(projectile);

        this.event.setDamage(100);
    }
}
