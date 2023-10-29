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
import teamzesa.dataValue.ColorList;
import teamzesa.dataValue.userData.User;
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;
import teamzesa.worldSet.Announcer;


public class JoinAndQuit implements Listener {
    private final UserMapHandler userMapHandler;
    private final Announcer announcer;

    private Player joinPlayer;
    private UserHandler userHandler;
//    private Player quitPlayer;

    public JoinAndQuit() {
        this.announcer = Announcer.getAnnouncer();
        this.userMapHandler = UserMapHandler.getUserHandler();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(@NotNull PlayerJoinEvent e) {
        this.joinPlayer = e.getPlayer();

        userHandling(); //User Object 추가
        addUserJoinCount(); //접속횟수
        userIPCheckUp(); //접속 IP 확인

        this.announcer.playerTab(this.joinPlayer);
        this.announcer.joinAnnouncer(this.joinPlayer);
        this.announcer.countAnnouncer(this.joinPlayer);

        attackSpeed();
        playerFlight(); //flight Set
        setHealthScale();
    }

    private void playerFlight() {
        this.joinPlayer.setAllowFlight(true);
        ComponentExchanger.playerAnnouncer(this.joinPlayer,"플라이 활성화",ColorList.YELLOW);
    }

    private void userHandling() {
        if (userMapHandler.getUser(this.joinPlayer.getUniqueId()) == null)
            userMapHandler.addUser(this.joinPlayer);
    }

    private void userIPCheckUp() {
        this.userHandler = new UserHandler(this.joinPlayer);
        int joinCnt = userHandler.getUser().getJoinCount();
        String message = joinCnt == 1 ? "신규 IP를 등록합니다." : "새로운 IP로 접속하셨습니다.";

//        접속유저의 IP가 이미 존재하면 Return
        if (userHandler.existsIP(this.joinPlayer.getAddress())) {
            if (joinCnt == 1) ComponentExchanger.playerAnnouncer(this.joinPlayer,message, ColorList.YELLOW);
            else return;
        }

        if (userHandler.addIP(this.joinPlayer.getAddress()))
            ComponentExchanger.playerAnnouncer(this.joinPlayer, message, ColorList.YELLOW);

        else Bukkit.getLogger().info(this.joinPlayer.getName() + " IP 추가 실패");
    }

    private void addUserJoinCount() {
        this.userHandler = new UserHandler(this.joinPlayer);
        userHandler.addJoinCnt();
    }

    private void setHealthScale() {
        User user = userMapHandler.getUser(this.joinPlayer.getUniqueId());
        this.joinPlayer.setHealthScale(user.getHealthScale());
        this.joinPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(user.getHealthScale());
    }

    private void attackSpeed() {
        this.joinPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
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
