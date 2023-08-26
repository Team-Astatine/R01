package teamzesa.userEvent;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.worldSet.Announcer;

import java.net.InetSocketAddress;
import java.util.List;

public class JoinAndQuit extends ComponentExchanger implements Listener {
    private final UserHandler userHandler;
    private final Announcer announcer;

    private Player joinPlayer;
    private User joinUser;
    private Player quitPlayer;

    public JoinAndQuit() {
        userHandler = UserHandler.getUserHandler();
        announcer = Announcer.getAnnouncer();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        joinPlayer = e.getPlayer();

//        ipChecking();
        attackSpeed();
        userHandling();
        setHealthScale();
        addUserJoinCount(); //접속횟수
        announcer.joinAnnouncer(joinPlayer);
    }

    /*private void ipChecking() {
        if (!userHandler.checkUpUserIp(joinPlayer))
            return;

        Bukkit.getLogger().info(joinPlayer.getName() + "님이 신규 IP로 접속했습니다.");
    }*/

    private void userHandling() {
        if (userHandler.getUser(joinPlayer.getUniqueId()) == null)
            userHandler.addUser(joinPlayer);
    }

    private void addUserJoinCount() {
        User user = userHandler.getUser(joinPlayer);
        user.setAddJoinCount();
    }

    private void setHealthScale() {
        User user = userHandler.getUser(joinPlayer.getUniqueId());
        joinPlayer.setHealthScale(user.getHealthScale());
        joinPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.getHealthScale());
    }

    private void attackSpeed() {
        joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player quitPlayer = e.getPlayer();
        double healthScale = quitPlayer.getHealthScale();
        if (healthScale != 20.0)
            userHandler.updateUser(quitPlayer.getUniqueId(), healthScale);
    }
}
