package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import teamzesa.entity.User;
import teamzesa.event.register.EventRegister;
 import teamzesa.util.Enum.WeaponMap;
import teamzesa.util.userHandler.UserController;

public class EntityAttackSpeedHandler implements EventRegister {
    private Entity damagerEntity;
    private Entity targetEntity;
    private User damagerUser;
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
        this.damagerUser = new UserController().readUser(this.damagerEntity.getUniqueId());

        if (!(this.targetEntity instanceof Player target))
            return;

        int hurtTick = 20; //default 20
        boolean stuffCheck = isDualWeaponChecker(
                damager.getInventory().getItemInMainHand().getType(),
                damager.getInventory().getItemInOffHand().getType());

        if (!stuffCheck) //One Hand Sword
            hurtTick = 10;

        if (stuffCheck || this.damagerUser.isGodMode()) //Two Hand Sword
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