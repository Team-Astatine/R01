package teamzesa.event;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Enum.WeaponMap;

public class EnhanceIncreaseDamageHandler implements EventRegister {

    private Entity damgerEntity;
    private final EntityDamageByEntityEvent event;

    public EnhanceIncreaseDamageHandler(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.damgerEntity = this.event.getDamager();
    }

    @Override
    public void execute() {
        if (!(this.damgerEntity instanceof Player damager)) {
            this.event.setCancelled(true);
            return;
        }

        ItemStack itemStack = damager.getInventory().getItemInMainHand();
        double damage = 0;
        int sharpnessEnchantLevel = itemStack.getEnchantLevel(Enchantment.DAMAGE_ALL);
        for (WeaponMap weaponMap : WeaponMap.values()) {
            if (itemStack.getType().equals(weaponMap.getMaterial())) {
                damage = sharpnessEnchantLevel + weaponMap.getDamage();
                break;
            }
        }


    }
}
