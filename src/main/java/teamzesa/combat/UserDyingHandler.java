package teamzesa.combat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.userValue.UserHandler;

public class UserDyingHandler implements Listener {
    private UserHandler userHandler;
    public UserDyingHandler() {
        userHandler = UserHandler.getUserHandler();
    }
    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent e) {
        userHandler.updateDyingUser(e.getEntity());
    }
}
