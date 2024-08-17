package teamzesa.event.EntityDamageByEntityEvent;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.Enhance.ShortRangeWeaponMap;

public class EntityAttackSpeedHandler implements EventRegister {
    private Entity damagerEntity;
    private Entity targetEntity;
    private final EntityDamageByEntityEvent event;

    public EntityAttackSpeedHandler(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.damagerEntity = this.event.getDamager();
        this.targetEntity = this.event.getEntity();
    }

    @Override
    public void execute() {
        if (!(this.damagerEntity instanceof Player damager))
            return;

        if (!(this.targetEntity instanceof Player target))
            return;

        int hurtTick = 10; //default 20
        boolean stuffCheck = isDualWeaponChecker(
                damager.getInventory().getItemInMainHand(),
                damager.getInventory().getItemInOffHand()
        );

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
        target.setMaximumNoDamageTicks(hurtTick);
    }

    private Boolean isDualWeaponChecker(ItemStack mainStuff, ItemStack offStuff) {
        ShortRangeWeaponMap mainHand = ShortRangeWeaponMap.findByItemStack(mainStuff);
        ShortRangeWeaponMap offHand = ShortRangeWeaponMap.findByItemStack(offStuff);

        if (mainHand.getMaterial().equals(Material.AIR))
            return false;

        if (offHand.getMaterial().equals(Material.AIR))
            return false;

        return true;
    }
}