package teamzesa.event.Enhance;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.Enhance.WeaponMap;

public class EnhanceWeaponHurtEvent extends EnhanceUtil implements EventRegister {
    private Player damager;
    private ItemStack weapon;
    private Entity entity;
    private final EntityDamageEvent event;

    public EnhanceWeaponHurtEvent(EntityDamageEvent event) {
        this.event = event;

        if (this.event.getDamageSource().getDirectEntity() instanceof Player player)
            this.damager = player;
        else return;

        init();
        execute();
    }

    @Override
    public void init() {
        this.weapon = this.damager.getInventory().getItemInMainHand();
    }

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
