package teamzesa.Event.PlayerInteraction.Announce.Tip;

import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.Data.DataIO.Config.ConfigIOHandler;
import teamzesa.Data.User.UserData.User;
import teamzesa.Data.User.UserData.UserController;
import teamzesa.R01;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.Function.ThreadPool;
import teamzesa.Util.Function.StringComponentExchanger;

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

    public void joinPlayerInformationAnnouncer(Player player) {
        int joinCnt = new UserController().readUser(player.getUniqueId()).joinCount();

        int ticks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long totalSeconds = ticks / 20L;
        long hrs = totalSeconds / 3600;
        long mins = (totalSeconds % 3600) / 60;
        long secs = totalSeconds % 60;
        String formatted = String.format("%02d시간 %02d분 %02d초", hrs, mins, secs);

        player.sendMessage(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorType.SKY_BLUE)
                        .append(componentExchanger(" " + joinCnt, ColorType.PINK))
                        .append(componentExchanger("번째 접속,", ColorType.SKY_BLUE))
                        .append(componentExchanger(" " + formatted, ColorType.PINK))
                        .append(componentExchanger(" 째 플레이 중!", ColorType.SKY_BLUE))
        );
    }

    public void setPlayerTabHeader(@NotNull Audience audience) {
        audience.sendPlayerListHeader(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorType.PURPLE)
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
                            Bukkit.getScheduler().runTask(
                                    R01.getPlugin(R01.class),
                                    ()-> player.performCommand("help"));
                    });
                },
                delay,
                interval
        );
    }
}
