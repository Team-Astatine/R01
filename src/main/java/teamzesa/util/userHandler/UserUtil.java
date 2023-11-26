package teamzesa.util.userHandler;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;

import java.net.InetSocketAddress;
import java.util.Set;

public class UserUtil {
    private final UserMapHandler userMapHandler = UserMapHandler.getUserMapHandler();
    private final User user;
    private final Set<String> ip;

    public UserUtil(User user) {
        this.user = user;
        this.ip = user.getIPList();
    }

    public UserUtil(Player player) {
        this.user = this.userMapHandler.getUser(player);
        this.ip = this.user.getIPList();
    }

    public Boolean existsIP(InetSocketAddress ip) {
        return this.ip.stream()
                .anyMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public Boolean nonExistsIP(InetSocketAddress ip) {
        return this.ip.stream()
                .noneMatch(listIP -> listIP.equals(ip.getAddress().getHostAddress()));
    }

    public void addIP(@NotNull InetSocketAddress ip) {
        this.ip.add(ip.getAddress().getHostAddress());
        this.user.setIp(this.ip);
        updateUser();
    }

    public void increaseJoinCnt() {
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
