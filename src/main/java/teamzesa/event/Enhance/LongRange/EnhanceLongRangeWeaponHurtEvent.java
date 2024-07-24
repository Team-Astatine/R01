package teamzesa.event.Enhance.LongRange;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.Enhance.LongRangeWeaponMap;

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
        if (!(this.damageSource.getCausingEntity() instanceof Player player))
            return;

        if (!(this.damageSource.getDirectEntity() instanceof Projectile))
            return;

        ItemStack weapon = checkModelData(player.getInventory().getItemInMainHand());

        double projectileDamage = 0.0;
        try {
            projectileDamage = LongRangeWeaponMap.findByItemStack(weapon).getLongRangeDamage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double projectilePowerIncreaseDmg = getArrowPowerDamage(weapon, projectileDamage);
//        10,11,12%
        double totalDamage = calculatingTotalEnhanceStageDamage(weapon, projectileDamage + projectilePowerIncreaseDmg);

        System.out.println("projectileDamage > " + projectileDamage);
        System.out.println("projectilePowerDmg > " + projectilePowerIncreaseDmg);
        System.out.println("totalDamage > " + totalDamage);

        this.event.setDamage(totalDamage);
    }
}
