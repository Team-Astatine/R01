package teamzesa.Event.Enhance.PlayerInteraction.Armour;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.Event.Enhance.EnhanceUtil;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Util.StringComponentExchanger;

public class EnhanceArmourResistanceArmour extends StringComponentExchanger implements EventRegister {

    private Entity targetEntity;
    private final EntityDamageEvent event;

    public EnhanceArmourResistanceArmour(EntityDamageEvent event) {
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
        ItemStack[] armour = new ItemStack[4];
        armour[0] = target.getInventory().getHelmet();
        armour[1] = target.getInventory().getChestplate();
        armour[2] = target.getInventory().getLeggings();
        armour[3] = target.getInventory().getBoots();

        double getDmg = this.event.getDamage();
        double percentage = EnhanceUtil.getCalculatingDefencePercentage(armour);
        if (percentage == 0.0)
            return;

        this.event.setDamage(getDmg - getDmg * percentage);
    }
}
