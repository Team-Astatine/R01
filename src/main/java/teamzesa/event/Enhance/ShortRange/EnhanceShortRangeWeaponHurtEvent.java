package teamzesa.event.Enhance.ShortRange;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceShortRangeWeaponHurtEvent extends EnhanceUtil implements EventRegister {
    private Player damager;
    private ItemStack weapon;
    private final EntityDamageEvent event;

    public EnhanceShortRangeWeaponHurtEvent(EntityDamageEvent event) {
        this.event = event;

        if (this.event.getDamageSource().getDirectEntity() instanceof Player player) {
            this.damager = player;
            this.weapon = player.getInventory().getItemInMainHand();
        }
        else return;
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

        double weaponDamage = getWeaponDamage(this.weapon);
        double enchantWeaponDamage = getSharpnessDamage(this.weapon, weaponDamage);
        double enhance = getEnhanceState(this.weapon, enchantWeaponDamage);

        this.event.setDamage(enhance);
    }
}
