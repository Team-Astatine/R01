package teamzesa.worldSet;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.R01;
import teamzesa.dataValue.userData.UserHandler;

import java.awt.*;

public class Announcer extends ComponentExchanger {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    private final ConfigIOHandler configIOHandler;
    private final UserHandler userHandler;

    private Announcer() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
        userHandler = UserHandler.getUserHandler();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }

    public void countAnnouncer(Player player) {
        playerAnnouncer(
                player,
                configIOHandler.getWorldMotdConfig() + "을 " +
                        String.valueOf(userHandler.getUser(player).getJoinCount())
                        + "회 접속하셨습니다.",
                new Color(191,0,255));
    }

    public void joinAnnouncer(Player player) {
        int count = userHandler.getUser(player).getJoinCount();
        System.out.println(count);
        String joinMention = "";

        if (count < 10) joinMention = "번째 접속";
        else if (count < 100) joinMention = "번 접속";
        else joinMention = " 접속";

        Component[] components = createComponents(
                String.valueOf(count) + joinMention
        );
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

    private Component[] createComponents(String title) {
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
