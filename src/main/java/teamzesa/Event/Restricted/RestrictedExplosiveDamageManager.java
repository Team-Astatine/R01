package teamzesa.Event.Restricted;

import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import teamzesa.Event.EventRegister.EventRegister;

public class RestrictedExplosiveDamageManager implements EventRegister {
    private Entity restrictedEntity;
    private final EntityDamageEvent event;

    public RestrictedExplosiveDamageManager(EntityDamageEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.restrictedEntity = this.event.getEntity();
    }

    @Override
    public void execute() {
        if (this.event.getDamageSource().getDamageType() != DamageType.EXPLOSION)
            return;

        switch (this.restrictedEntity.getType()) {
            case GHAST, ENDER_DRAGON, WITHER -> this.event.setCancelled(true);
        }
    }
}
