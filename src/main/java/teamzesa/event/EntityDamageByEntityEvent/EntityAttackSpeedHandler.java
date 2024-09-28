package teamzesa.event.EntityDamageByEntityEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.Enhance.ShortRangeWeapon;

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
        if (ObjectUtils.notEqual(this.damagerEntity.getType(), EntityType.PLAYER))
            return;

        if (ObjectUtils.notEqual(this.targetEntity.getType(), EntityType.PLAYER))
            return;

        Player damager = (Player) this.damagerEntity;
        Player target = (Player) this.targetEntity;

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
        ShortRangeWeapon mainHand = ShortRangeWeapon.findByItemStack(mainStuff);
        ShortRangeWeapon offHand = ShortRangeWeapon.findByItemStack(offStuff);

        if (mainHand.getMaterial().equals(Material.AIR))
            return false;

        if (offHand.getMaterial().equals(Material.AIR))
            return false;

        return true;
    }
}