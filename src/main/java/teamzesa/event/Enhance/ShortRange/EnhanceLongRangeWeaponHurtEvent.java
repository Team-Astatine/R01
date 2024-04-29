package teamzesa.event.Enhance.ShortRange;

import org.bukkit.event.entity.EntityDamageEvent;
import org.w3c.dom.events.Event;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceLongRangeWeaponHurtEvent extends EnhanceUtil implements EventRegister {

    private final EntityDamageEvent event;

    public EnhanceLongRangeWeaponHurtEvent(EntityDamageEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void execute() {

    }

    @Override
    public void init() {

    }
}
