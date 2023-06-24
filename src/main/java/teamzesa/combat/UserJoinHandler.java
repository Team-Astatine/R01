package teamzesa.combat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import teamzesa.user.User;
import teamzesa.user.UserHandler;

public class UserJoinHandler implements Listener {
    private UserHandler userHandler;

    public UserJoinHandler() {
        userHandler = UserHandler.getInstance();
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
}
