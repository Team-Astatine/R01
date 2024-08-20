package teamzesa.event.Enhance.Armor;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceArmourResistanceHelmet implements EventRegister {

    private Entity targetEntity;
    private final EntityDamageByEntityEvent event;

    public EnhanceArmourResistanceHelmet(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.targetEntity = this.event.getEntity();
    }

    @Override
    public void execute() {
        if (ObjectUtils.notEqual(this.targetEntity.getType(), EntityType.PLAYER))
            return;

        Player target = (Player) this.targetEntity;
        ItemStack armour = target.getInventory().getHelmet();

        if (armour == null)
            return;

        if (armour.getItemMeta() == null)
            return;

        if (BooleanUtils.isFalse(armour.getItemMeta().hasCustomModelData()))
            return;

        double totalDmg = EnhanceUtil.calculatingTotalResistanceDamage(armour, this.event.getFinalDamage());

        this.event.setDamage(totalDmg);
    }
}
