package teamzesa.attackSpeed;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityNoDamageSet implements Listener {

    @EventHandler
    public boolean entityHit(EntityDamageByEntityEvent e) {
        if (e.getEntityType() != EntityType.PLAYER)
            return false;

        if (e.getDamager().getType() != EntityType.PLAYER)
            return false;

        Player target = (Player) e.getEntity();
        //default 20
        target.setMaximumNoDamageTicks(1);

        return true;
    }
}
