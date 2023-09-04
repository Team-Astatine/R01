package teamzesa.worldSet;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.R01;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;

public class Announcer extends ComponentExchanger {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    private final ConfigIOHandler configIOHandler;
    private final UserMapHandler userMapHandler;

    private Announcer() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
        userMapHandler = UserMapHandler.getUserHandler();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }

    public void countAnnouncer(Player player) {
        int joinCnt = userMapHandler.getUser(player).getJoinCount();
        player.sendMessage(
                componentSet("ASTATINE ONLINE",new Color(173,216,230))
                        .append(componentSet(" "+String.valueOf(joinCnt),new Color(255,182,193)))
                        .append(componentSet("번째 접속!",new Color(173,216,230)))
        );
    }

    public void playerTab(@NotNull Audience player) {
        player.sendPlayerListHeader(
                componentSet("ASTATINE ONLINE",new Color(139, 0, 255))
        );
    }

    public void joinAnnouncer(Player player) {
        Component[] components = createComponents("환영합니다");
        for (Component component : components)
            player.sendMessage(component);
    }

    public void defaultAnnouncer() {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents("클릭하세요");
        Bukkit.getScheduler().runTaskTimer(R01.getPlugin(R01.class), () -> {
            for (Component component : components)
                Bukkit.broadcast(component);
        }, delay, interval);
    }

    private Component @NotNull [] createComponents(String title) {
        String mineListLink = configIOHandler.getMineListConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String notionLink = configIOHandler.getNotionConfig();

        Component firstLine = componentSet(String.format("================[%s]================",title), new Color(221, 255, 170));
        Component mineList = createLinkComponent("[클릭] > 마인리스트 추천", mineListLink, new Color(167, 123, 202));
        Component discord = createLinkComponent("[클릭] > 디스코드 참가", discordLink, new Color(114, 137, 218));
        Component notion = createLinkComponent("[클릭] > 서버 플레이 가이드", notionLink, new Color(112, 71, 157));
        Component secondLine = componentSet("========================================", new Color(221, 255, 170));

        return new Component[]{firstLine, mineList, discord, notion, secondLine};
    }
}
