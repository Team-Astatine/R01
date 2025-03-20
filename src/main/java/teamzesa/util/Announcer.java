package teamzesa.util;

import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.R01;
import teamzesa.Enum.ColorList;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public class Announcer extends StringComponentExchanger {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }


    private final ConfigIOHandler configIOHandler;

    private Announcer() {
        this.configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    public void countAnnouncer(Player player) {
        int joinCnt = new UserController().readUser(player.getUniqueId()).joinCount();
        player.sendMessage(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorList.SKY_BLUE)
                        .append(componentExchanger(" " + joinCnt, ColorList.PINK))
                        .append(componentExchanger("번째 접속!", ColorList.SKY_BLUE))
        );
    }

    public void setPlayerTabHeader(@NotNull Audience audience) {
        audience.sendPlayerListHeader(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorList.PURPLE)
        );
    }

    /**
     * 서버 공지사항을 5분마다 공지합니다.
     * @see ThreadPool#addSchedulingTaskMin(Runnable, long, long) 을 참조해주세요. 
     */
    public void defaultAnnouncer() {
        long delay = 0;
        long interval = 5;
        ThreadPool.getThreadPool().addSchedulingTaskMin(() -> {
                    List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                    if (players.isEmpty())
                        return;

                    players.forEach(player -> {
                        User user = new UserController().readUser(player.getUniqueId());
                        if (user.announcingSkip())
                            Bukkit.getScheduler().runTask(R01.getPlugin(R01.class), ()-> player.performCommand("help"));
                    });
                },
                delay,
                interval
        );
    }
}
