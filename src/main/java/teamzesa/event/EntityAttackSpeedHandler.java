package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import teamzesa.event.EventRegister.EventRegister;
 import teamzesa.util.Enum.Enhance.WeaponMap;

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

        int hurtTick = 20; //default 20
        boolean stuffCheck = isDualWeaponChecker(
                damager.getInventory().getItemInMainHand().getType(),
                damager.getInventory().getItemInOffHand().getType());

        if (!stuffCheck) //One Hand Sword
            hurtTick = 10;

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
        target.setMaximumNoDamageTicks(hurtTick);
    }

    private Boolean isDualWeaponChecker(Material mainStuff, Material offStuff) {
        for (WeaponMap weaponMapInfo : WeaponMap.values()) {
            Material material = weaponMapInfo.getMaterial();
            if (material.equals(mainStuff) && material.equals(offStuff))
                return true;
        }
        return false;
    }
}