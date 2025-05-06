package teamzesa.Event.Enhance.PlayerInteraction.ShortRange;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.Event.Enhance.Interface.EnhanceUtil;
import teamzesa.Event.EventRegister;

public class EnhanceShortRangeWeaponHurtEvent implements EventRegister {
    private Entity causingEntity;
    private Entity directEntity;
    private final EntityDamageByEntityEvent event;

    public EnhanceShortRangeWeaponHurtEvent(EntityDamageByEntityEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.causingEntity = this.event.getDamageSource().getCausingEntity();
        this.directEntity = this.event.getDamageSource().getDirectEntity();
    }

    @Override
    public void execute() {
         if (ObjectUtils.anyNull(this.causingEntity, this.directEntity))
            return;

        if (ObjectUtils.notEqual(this.causingEntity.getType(), EntityType.PLAYER))
            return;

        if (ObjectUtils.notEqual(this.directEntity.getType(), EntityType.PLAYER))
            return;

        Player player = (Player) this.directEntity;
        ItemStack weapon = player.getInventory().getItemInMainHand();

        if (ObjectUtils.isEmpty(weapon.getItemMeta()))
            return;

        if (BooleanUtils.isFalse(weapon.getItemMeta().hasCustomModelData()))
            return;

        double eventDamage = this.event.getDamage(); //getOriginalDamage

        eventDamage = this.event.isCritical() ? eventDamage / 1.5 : eventDamage; //Remove Critical Damage

        double resultDmg = EnhanceUtil.getCalculatingDamagePercentage(weapon, eventDamage);// 11, 12, 13% Increase Dmg
        resultDmg = this.event.isCritical() ? resultDmg * 1.5 : resultDmg; //Add Critical Damage

        this.event.setDamage(resultDmg);

        /* debug fixme
        double eventFinalDamage = this.event.getFinalDamage();
        System.out.println("finalDmg > " + eventFinalDamage);
        eventFinalDamage = this.event.isCritical() ? eventFinalDamage / 1.5 : eventFinalDamage;
        System.out.println("finalDmg remove Critical > " + eventFinalDamage);

        double resultDmg = calculatingTotalEnhanceStageDamage(weapon, eventFinalDamage);// 11, 12, 13% Increase Dmg
        System.out.println("finalDmg calculation enhance DMG > " + resultDmg);
        resultDmg = this.event.isCritical() ? resultDmg * 1.5 : resultDmg;

        System.out.println("finalDmg calculation critical DMG > " + resultDmg);
        System.out.println();
        this.event.setDamage(resultDmg);
         */
    }
}
