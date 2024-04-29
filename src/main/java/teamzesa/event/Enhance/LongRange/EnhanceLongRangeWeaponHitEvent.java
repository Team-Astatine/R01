package teamzesa.event.Enhance.LongRange;

import org.bukkit.event.entity.ProjectileHitEvent;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceLongRangeWeaponHitEvent implements EventRegister {
    private final ProjectileHitEvent event;

    public EnhanceLongRangeWeaponHitEvent(ProjectileHitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {

    }

    @Override
    public void execute() {

    }
}
