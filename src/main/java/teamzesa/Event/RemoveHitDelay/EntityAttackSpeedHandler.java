package teamzesa.Event.RemoveHitDelay;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.Event.EventRegister;
import teamzesa.Event.Enhance.Enumeration.Weapon.ShortRange;
import teamzesa.R01;

public class EntityAttackSpeedHandler implements EventRegister {
    private Entity damagerEntity;
    private LivingEntity targetEntity;
    private final EntityDamageByEntityEvent event;

    public EntityAttackSpeedHandler(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.damagerEntity = this.event.getDamager();
        this.targetEntity = this.event.getEntity() instanceof LivingEntity entity ? entity : null;
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.targetEntity))
            return;

        if (ObjectUtils.notEqual(this.damagerEntity.getType(), EntityType.PLAYER))
            return;

        Player damager = (Player) this.damagerEntity;

        int hurtTick = 10; //default 20

        boolean stuffCheck = isDualWeaponChecker(
                damager.getInventory().getItemInMainHand(),
                damager.getInventory().getItemInOffHand()
        );
        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;


//        Bukkit.getScheduler().runTaskLater(R01.getPlugin(R01.class),
//                () -> targetEntity.setVelocity(targetEntity.getVelocity().multiply(hitDelay)), 1);

//        final int tick = hurtTick;
//        Bukkit.getScheduler().runTaskLater(
//                R01.getPlugin(R01.class),
//                () -> this.targetEntity.setMaximumNoDamageTicks(tick),
//                0
//        );

        this.targetEntity.setMaximumNoDamageTicks(hurtTick);
    }

    private Boolean isDualWeaponChecker(ItemStack mainStuff, ItemStack offStuff) {
        ShortRange mainHand = ShortRange.findByItemStack(mainStuff);
        ShortRange offHand = ShortRange.findByItemStack(offStuff);

        if (mainHand.getMaterial().equals(Material.AIR))
            return false;

        if (offHand.getMaterial().equals(Material.AIR))
            return false;

        return true;
    }
}