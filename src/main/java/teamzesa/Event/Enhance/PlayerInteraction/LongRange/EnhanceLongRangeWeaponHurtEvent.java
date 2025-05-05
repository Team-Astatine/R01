package teamzesa.Event.Enhance.PlayerInteraction.LongRange;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.Event.Enhance.EnhanceUtil;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Enumeration.Enhance.Items.Weapon.LongRange;

public class EnhanceLongRangeWeaponHurtEvent implements EventRegister {
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

        ItemStack weapon = EnhanceUtil.initItemCustomModelData(player.getInventory().getItemInMainHand());

        double projectileDamage = LongRange.findByItemStack(weapon).getLongRangeDamage();
        double projectilePowerIncreaseDmg = EnhanceUtil.getArrowPowerDamage(weapon, projectileDamage);

//        10,11,12%
        double totalDamage = EnhanceUtil.getCalculatingDamagePercentage(
                weapon, projectileDamage + projectilePowerIncreaseDmg);

        System.out.println("projectileDamage > " + projectileDamage);
        System.out.println("projectilePowerDmg > " + projectilePowerIncreaseDmg);
        System.out.println("totalDamage > " + totalDamage);

        this.event.setDamage(totalDamage);
    }
}
