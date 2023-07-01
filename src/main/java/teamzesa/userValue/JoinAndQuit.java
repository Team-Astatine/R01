package teamzesa.userValue;

import org.bukkit.attribute.Attribute;
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

        if (userHandler.getUser(player.getUniqueId()) == null)
            userHandler.addUser(player);

        User user = userHandler.getUser(player.getUniqueId());

        player.setHealthScale(user.getHealthScale());
        player.setPlayerListName(user.getKoreanName());
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (player.getHealthScale() == 20.0)
            return;

        userHandler.updateUser(player.getUniqueId(), player.getHealthScale());
    }
}
