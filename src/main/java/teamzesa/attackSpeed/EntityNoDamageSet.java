package teamzesa.attackSpeed;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityNoDamageSet implements Listener {

    @EventHandler
    public void entityHit(EntityDamageByEntityEvent e) {
        LivingEntity target = (LivingEntity) e.getEntity();
        //default 20
        target.setMaximumNoDamageTicks(1);
    }
}
