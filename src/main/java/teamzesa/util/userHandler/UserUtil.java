package teamzesa.util.userHandler;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.dataValue.User;

import java.net.InetSocketAddress;
import java.util.Set;

public class UserUtil {
    private final UserMapHandler userMapHandler = UserMapHandler.getUserMapHandler();
    private User user;
    private Set<String> ip;

    public UserUtil(Player player) {
        this.user = this.userMapHandler.getUser(player);
        this.ip = user.getIPList();
    }

    public Boolean existsIP(InetSocketAddress ip) {
        return this.ip.stream()
                .anyMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public Boolean existsNotIP(InetSocketAddress ip) {
        return this.ip.stream()
                .noneMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public void addIP(@NotNull InetSocketAddress ip) {
        this.ip.add(ip.getAddress().getHostAddress());
        this.user.setIp(this.ip);
        updateUser();
    }

    public void addJoinCnt() {
        this.user.setJoinCount(this.user.getJoinCount() + 1);
        updateUser();
    }


    public void updateUser() {
        this.userMapHandler.updateUser(this.user);
    }

    public User getUser() {
        return this.user;
    }
}
