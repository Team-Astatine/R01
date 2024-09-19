package teamzesa.event.EntityDamageByEntityEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import teamzesa.event.EventRegister.EventRegister;

public class EntityAttackSpeedClear implements EventRegister {

    private Player player;
    private final PlayerInteractEvent event;

    public EntityAttackSpeedClear(PlayerInteractEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
    }

    @Override
    public void execute() {
//        default == 20
        if (this.player.getMaximumNoDamageTicks() == 20)
            return;

        this.player.setMaximumNoDamageTicks(20);
    }
}
