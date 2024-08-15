package teamzesa.event.Enhance.Armor;

import org.bukkit.event.entity.EntityDamageEvent;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceResistanceEvent implements EventRegister {

    private final EntityDamageEvent event;

    public EnhanceResistanceEvent(EntityDamageEvent event) {
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
