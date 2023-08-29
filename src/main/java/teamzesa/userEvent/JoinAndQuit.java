package teamzesa.userEvent;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.worldSet.Announcer;

import java.awt.*;

public class JoinAndQuit extends ComponentExchanger implements Listener {
    private final UserMapHandler userMapHandler;
    private final Announcer announcer;

    private Player joinPlayer;
    private User joinUser;
//    private Player quitPlayer;

    public JoinAndQuit() {
        userMapHandler = UserMapHandler.getUserHandler();
        announcer = Announcer.getAnnouncer();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(@NotNull PlayerJoinEvent e) {
        joinPlayer = e.getPlayer();
        joinUser = new User(joinPlayer);

//        ipChecking();
        attackSpeed();
        userHandling();
        setHealthScale();
        userIPCheckUp();
        addUserJoinCount(); //접속횟수

        announcer.playerTab(joinPlayer);
        announcer.countAnnouncer(joinPlayer);
        announcer.joinAnnouncer(joinPlayer);
    }

    private void userHandling() {
        if (userMapHandler.getUser(joinPlayer.getUniqueId()) == null)
            userMapHandler.addUser(joinPlayer);
    }

    private void userIPCheckUp() {
        UserHandler userHandler = new UserHandler(joinUser);

//        접속 유저가 처음 접속 이면 return
        if (joinUser.getJoinCount() == 1)
            return;

//        접속유저의 IP가 등록되어 있지 않다면?
        if (!userHandler.existsIP(joinPlayer.getAddress()))
            return;

        if (userHandler.addIP(joinPlayer.getAddress()))
            playerAnnouncer(joinPlayer,"새로운 IP로 접속하셨습니다.",Color.YELLOW);
        else Bukkit.getLogger().info(joinPlayer.getName() + " IP 추가 실패");
    }

    private void addUserJoinCount() {
        UserHandler user = new UserHandler(userMapHandler.getUser(joinPlayer));
        user.addJoinCnt();
    }

    private void setHealthScale() {
        User user = userMapHandler.getUser(joinPlayer.getUniqueId());
        joinPlayer.setHealthScale(user.getHealthScale());
        joinPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.getHealthScale());
    }

    private void attackSpeed() {
        joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        /*
        Player quitPlayer = e.getPlayer();
        double healthScale = quitPlayer.getHealthScale();

//        valid healthScale
        if (healthScale != 20.0)
            userMapHandler.updateUser(quitPlayer.getUniqueId(), healthScale);
        */
    }
}
