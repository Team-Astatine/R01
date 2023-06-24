package teamzesa.combat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.user.User;
import teamzesa.user.UserHandler;

public class UserDyingHandler implements Listener {
    private UserHandler userHandler;
    public UserDyingHandler() {
        userHandler = UserHandler.getInstance();
    }
    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent e) {
        userHandler.updateDyingUser(e.getEntity());
    }
}
