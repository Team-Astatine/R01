package teamzesa.event.Enhance.PlayerInteraction.ShortRange;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceShortRangeWeaponHurtEvent extends EnhanceUtil implements EventRegister {
    private DamageSource damageSource;
    private final EntityDamageByEntityEvent event;

    public EnhanceShortRangeWeaponHurtEvent(EntityDamageByEntityEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.damageSource = this.event.getDamageSource();
    }

    @Override
    public void execute() {
        if (!(this.damageSource.getCausingEntity() instanceof Player))
            return;

        if (!(this.damageSource.getDirectEntity() instanceof Player player))
            return;

        ItemStack weapon = player.getInventory().getItemInMainHand();

        if (ObjectUtils.allNull(weapon.getItemMeta()))
            return;

        if (BooleanUtils.isFalse(weapon.getItemMeta().hasCustomModelData()))
            return;

        double eventDamage = this.event.getDamage(); //getOriginalDamage

        eventDamage = this.event.isCritical() ? eventDamage / 1.5 : eventDamage; //Remove Critical Damage

        double resultDmg = calculatingTotalEnhanceStageDamage(weapon, eventDamage);// 11, 12, 13% Increase Dmg
        resultDmg = this.event.isCritical() ? resultDmg * 1.5 : resultDmg; //Add Critical Damage

        this.event.setDamage(resultDmg);

        /* Debugging Code
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
