package teamzesa.event.Enhance.ShortRange;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceShortRangeWeaponHurtEvent extends EnhanceUtil implements EventRegister {
    private DamageSource damageSource;
    private final EntityDamageEvent event;

    public EnhanceShortRangeWeaponHurtEvent(EntityDamageEvent event) {
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
        if (!(this.damageSource.getCausingEntity() instanceof Player))
            return;

        if (!(this.damageSource.getDirectEntity() instanceof Player player))
            return;

        ItemStack weapon = player.getInventory().getItemInMainHand();

        if (weapon.getItemMeta() == null)
            return;

        if (!weapon.getItemMeta().hasCustomModelData())
            return;

        double weaponDmg = getShortRangeDamage(weapon) + getSharpnessDamage(weapon);
        double totalDmg = calculatingTotalEnhanceStageDamage(weapon, weaponDmg); // 10,12,14%
        this.event.setDamage(totalDmg);
    }
}
