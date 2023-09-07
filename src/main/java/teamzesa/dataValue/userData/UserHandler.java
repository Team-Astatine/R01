package teamzesa.dataValue.userData;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.UUID;

public class UserHandler {
    private final UserMapHandler userMapHandler = UserMapHandler.getUserHandler();
    private User user;
    private UUID uuid;
    private String name;
    private Set<String> ip;
    private int joinCount;
    private int level;
    private double healthScale;
    private boolean godMode;

    public UserHandler(Player player) {
        this.user = userMapHandler.getUser(player);
        this.ip = user.getIPList();
    }

    public UserHandler(@NotNull User user) {
        this.user = user;
        this.ip = user.getIPList();
        this.godMode = user.isGodMode();
    }

    public Boolean existsIP(InetSocketAddress ip) {
        return this.ip
                .stream()
                .anyMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public boolean addIP(@NotNull InetSocketAddress ip) {
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
        user.setGodMode(enable);
        updateUser();
    }

    public void setHealthScale(double healthScale) {
        user.setHealthScale(healthScale);
        updateUser();
    }

    public void updateUser() {
        userMapHandler.updateUser(user);
    }

    public User getUser() {
        return user;
    }
}
