package teamzesa.event;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnhanceStuffEvent implements EventRegister{
    private Action action;
    private final PlayerInteractEvent event;

    public EnhanceStuffEvent(PlayerInteractEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.action = event.getAction();
    }

    @Override
    public void execute() {

    }
}
