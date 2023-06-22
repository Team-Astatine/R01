package teamzesa.comabt;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityNoDamageSet implements Listener {

    @EventHandler
    public void entityHit(EntityDamageByEntityEvent e) {
        if (e.getEntityType() != EntityType.PLAYER)
            return;

        if (e.getDamager().getType() != EntityType.PLAYER)
            return;

        Player target = (Player) e.getEntity();
        //default 20
        target.setMaximumNoDamageTicks(1);
    }
}