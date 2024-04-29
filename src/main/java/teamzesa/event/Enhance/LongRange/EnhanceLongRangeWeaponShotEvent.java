package teamzesa.event.Enhance.LongRange;

import org.bukkit.event.entity.ProjectileLaunchEvent;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceLongRangeWeaponShotEvent implements EventRegister {
    private final ProjectileLaunchEvent event;

    public EnhanceLongRangeWeaponShotEvent(ProjectileLaunchEvent event) {
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
