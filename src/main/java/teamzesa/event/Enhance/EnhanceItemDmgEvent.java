package teamzesa.event.Enhance;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Enum.WeaponMap;

public class EnhanceItemDmgEvent implements EventRegister {
    private static final double ENHANCE_INCREASE_DAMAGE_PERCENTAGE = 10;

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
        } else this.event.setCancelled(true);
    }

    @Override
    public void execute() {
        if (this.damager == null)
            return;

        if (!this.weapon.hasCustomModelData())
            return;

        double baseDamage = getBaseDamage();
        double enchantmentDamage = calculateEnchantmentDamage();
        double modelDataDamage = calculateModelDataDamage();
        double totalDamage = baseDamage + enchantmentDamage + modelDataDamage;

        this.event.setDamage(totalDamage);
    }

    private double getBaseDamage() {
        for (WeaponMap weaponInfo : WeaponMap.values()) {
            if (weaponInfo.getMaterial().equals(this.weapon.getType()))
                return weaponInfo.getDamage();
        }
        return 0;
    }

    private double calculateEnchantmentDamage() {
        int enchantLevel = this.weapon.getEnchantLevel(Enchantment.DAMAGE_ALL);
        double enchantmentDamage = 0;
        for (int i = 0; i < enchantLevel; i++) {
            switch (i) {
                case 0 -> enchantmentDamage += 1;
                case 1, 2, 3, 4 -> enchantmentDamage += 0.5;
            }
        }
        return enchantmentDamage;
    }

    private double calculateModelDataDamage() {
        double damage = getBaseDamage();
        double modelData = this.weapon.getCustomModelData();
        double modelDataDamage = 0;

        for (int i = 1; i < modelData; i++) {
            double increaseDmg = damage * (ENHANCE_INCREASE_DAMAGE_PERCENTAGE + i) / 100;
            modelDataDamage += increaseDmg;
        }
        return modelDataDamage;
    }

}
