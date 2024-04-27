package teamzesa.event.Enhance;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.Enhance.WeaponMap;

public class EnhanceItemDmgEvent implements EventRegister {
    private static final double ENHANCE_INCREASE_DAMAGE_PERCENTAGE = 10;

    private Player damager;
    private ItemStack weapon;
    private Entity entity;
    private final EntityDamageEvent event;

    public EnhanceItemDmgEvent(EntityDamageEvent event) {
        this.event = event;

        if (this.event.getDamageSource().getDirectEntity() instanceof Player player)
            this.damager = player;
        else return;

        init();
        execute();
    }

    @Override
    public void init() {
        this.damager = (Player) this.entity;
        this.weapon = this.damager.getInventory().getItemInMainHand();
    }

    @Override
    public void execute() {
        if (this.damager == null)
            return;

        if (!this.weapon.hasCustomModelData())
            return;

        double modelData = this.weapon.getCustomModelData();
        double currentWeaponDamage = getBaseDamage() + calculateEnchantmentDamage();

        for (int i = 0; i < modelData; i++) {
            double increasePercentage = ENHANCE_INCREASE_DAMAGE_PERCENTAGE + (i * 2);
            currentWeaponDamage += currentWeaponDamage * (increasePercentage / 100);
        }

        this.event.setDamage(currentWeaponDamage);
    }

    private double getBaseDamage() {
        for (WeaponMap weaponInfo : WeaponMap.values()) {
            if (weaponInfo.getMaterial().equals(this.weapon.getType()))
                return weaponInfo.getDamage();
        }
        return 0;
    }

    private double calculateEnchantmentDamage() {
        double enchantmentDamage = 0;
        int enchantLevel = this.weapon.getEnchantLevel(Enchantment.DAMAGE_ALL);
        for (int i = 0; i < enchantLevel; i++) {
            switch (i) {
                case 0 -> enchantmentDamage += 1;
                case 1, 2, 3, 4 -> enchantmentDamage += 0.5;
            }
        }
        return enchantmentDamage;
    }
}
