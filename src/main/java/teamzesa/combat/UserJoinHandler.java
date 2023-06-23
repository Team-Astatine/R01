package teamzesa.combat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import teamzesa.user.UserHandler;

public class UserJoinHandler implements Listener {
    private UserHealthScaleHandler playerExpHealthScale;
    private UserHandler userHandler;

    public UserJoinHandler() {
        playerExpHealthScale = new UserHealthScaleHandler();
        userHandler = UserHandler.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        playerExpHealthScale.expChangeEvent(player);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);

        if (userHandler.getUser(player) == null)
            userHandler.addUser(player);
    }
}
