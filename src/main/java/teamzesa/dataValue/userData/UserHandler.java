package teamzesa.dataValue.userData;

import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;

public class UserHandler {
    private final UserMapHandler userMapHandler = UserMapHandler.getUserHandler();
    private User user;
    private UUID uuid;
    private String name;
    private List<String> ip;
    private int joinCount;
    private int level;
    private double healthScale;
    private boolean godMode;

    public UserHandler(Player player) {
        this.user = userMapHandler.getUser(player);
        this.ip = user.getIPList();
    }

    public UserHandler(User user) {
        this.user = user;
        this.ip = user.getIPList();
        this.godMode = user.isGodMode();
    }

    public Boolean existsIP(InetSocketAddress ip) {
        return this.ip
                .stream()
                .anyMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public boolean addIP(InetSocketAddress ip) {
        this.ip.add(ip.getAddress().getHostAddress());
        user.setIp(this.ip);

        updateUser();
        return true;
    }

    public void addJoinCnt() {
        user.setJoinCount(user.getJoinCount() + 1);
        updateUser();
    }

    public boolean isGodMode() {
        return godMode;
    }

    public void setGodMode(boolean enable) {
        user.setGodMode(true);
        updateUser();
    }

    public void updateUser() {
        userMapHandler.updateUser(user);
    }

    public User getUser() {
        return user;
    }
}
