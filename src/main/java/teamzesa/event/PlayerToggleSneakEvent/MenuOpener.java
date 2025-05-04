package teamzesa.event.PlayerToggleSneakEvent;

import org.bukkit.event.player.PlayerToggleSneakEvent;
import teamzesa.event.EventRegister.EventRegister;

public class MenuOpener implements EventRegister {



    private final PlayerToggleSneakEvent event;

    public MenuOpener(PlayerToggleSneakEvent event) {
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
