package teamzesa.combat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageTicking implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityHit(EntityDamageByEntityEvent e) {
        if (e.getEntityType() != EntityType.PLAYER)
            return;

        if (e.getDamager().getType() != EntityType.PLAYER)
            return;

        Player target = (Player) e.getEntity();
        //default 20
        target.setMaximumNoDamageTicks(1);
//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
    }
}
