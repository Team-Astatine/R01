package teamzesa.userValue;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndQuit implements Listener {
    private UserHandler userHandler;

    public JoinAndQuit() {
        userHandler = UserHandler.getUserHandler();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        userHandling(player);
        setHealthScale(player);
    }

    private void userHandling(Player player) {
        if (userHandler.getUser(player.getUniqueId()) == null)
            userHandler.addUser(player);
    }

    private void setHealthScale(Player player) {
        User user = userHandler.getUser(player.getUniqueId());
        if (user != null)
            player.setHealthScale(user.getHealthScale());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        double healthScale = player.getHealthScale();
        if (healthScale != 20.0)
            userHandler.updateUser(player.getUniqueId(), healthScale);
    }
}
