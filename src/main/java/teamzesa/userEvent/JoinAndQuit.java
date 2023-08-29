package teamzesa.userEvent;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Horse;
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
    private UserHandler userHandler;
//    private Player quitPlayer;

    public JoinAndQuit() {
        announcer = Announcer.getAnnouncer();
        userMapHandler = UserMapHandler.getUserHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(@NotNull PlayerJoinEvent e) {
        joinPlayer = e.getPlayer();

        userHandling(); //User Object 추가
        addUserJoinCount(); //접속횟수
        userIPCheckUp(); //접속 IP 확인

        attackSpeed();
        setHealthScale();

        announcer.playerTab(joinPlayer);
        announcer.countAnnouncer(joinPlayer);
        announcer.joinAnnouncer(joinPlayer);
    }

    private void userHandling() {
        if (userMapHandler.getUser(joinPlayer.getUniqueId()) == null)
            userMapHandler.addUser(joinPlayer);
    }

    private void userIPCheckUp() {
        this.userHandler = new UserHandler(joinPlayer);
        int joinCnt = userHandler.getUser().getJoinCount();
        String message = joinCnt == 1 ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";

//        접속유저의 IP가 이미 존재하면 Return
        if (userHandler.existsIP(joinPlayer.getAddress())) {
            if (joinCnt == 1) playerAnnouncer(joinPlayer,message, Color.YELLOW);
            else return;
        }

        if (userHandler.addIP(joinPlayer.getAddress()))
            playerAnnouncer(joinPlayer, message, Color.YELLOW);

        else Bukkit.getLogger().info(joinPlayer.getName() + " IP 추가 실패");
    }

    private void addUserJoinCount() {
        this.userHandler = new UserHandler(joinPlayer);
        userHandler.addJoinCnt();
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
