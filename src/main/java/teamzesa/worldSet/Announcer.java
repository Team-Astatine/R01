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
        Component[] components = createComponents("환영합니다",1);
        for (Component component : components)
            player.sendMessage(component);

        Component[] serverTip = createComponents("tip", 2);
        for (Component component : serverTip)
            Bukkit.broadcast(component);
    }

    public void defaultAnnouncer() {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents("클릭하세요",1);
        Bukkit.getScheduler().runTaskTimer(R01.getPlugin(R01.class), () -> {
            for (Component component : components)
                Bukkit.broadcast(component);
        }, delay, interval);
    }

    public void commandAnnouncer() {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents("서 버 팁",2);
        Bukkit.getScheduler().runTaskTimer(R01.getPlugin(R01.class), () -> {
            for (Component component : components)
                Bukkit.broadcast(component);
        }, delay, interval);
    }

    private Component[] createComponents(String title,int menu) {
        String mineListLink = configIOHandler.getMineListConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String notionLink = configIOHandler.getNotionConfig();

        if (menu == 1) {
            Component firstLine = componentSet(String.format("================[%s]================", title), new Color(221, 255, 170));
            Component mineList = createLinkComponent("[클릭] > 마인리스트 추천", mineListLink, new Color(167, 123, 202));
            Component discord = createLinkComponent("[클릭] > 디스코드 참가", discordLink, new Color(114, 137, 218));
            Component notion = createLinkComponent("[클릭] > 서버 플레이 가이드", notionLink, new Color(112, 71, 157));
            Component secondLine = componentSet("========================================", new Color(221, 255, 170));
            return new Component[]{firstLine, mineList, discord, notion, secondLine};
        }

        if (menu == 2) {
            Component steelLifeTip = componentSet("플레이어 사살시 체력을 약탈합니다.", new Color(233, 30, 99));
            Component raidTip = componentSet("습격 발생시 해당 좌표 지점이 공지됩니다.", new Color(233, 30, 99));
            Component weaponTip = componentSet("네더라이트 검 or 도끼를 양손에 들시 공격 속도가 매우 빨라 집니다.", new Color(233, 30, 99));
            Component explosiveTip = componentSet("모든 폭팔을 조심하세요. 크리퍼, 가스트, 위더, TNT 모든게 \"매우\"위험 합니다.", new Color(233, 30, 99));
            Component commandFly = componentSet("/fly - 하늘을 납니다.", new Color(139, 195, 74));
            Component commandHat = componentSet("/머리 - 손에 든 아이템을 머리에 씁니다.", new Color(139, 195, 74));
            Component commandTotem = componentSet("/토템 - 인벤토리의 토템을 모두 합칩니다.", new Color(139, 195, 74));
            return new Component[]{steelLifeTip, raidTip, weaponTip, explosiveTip, commandFly, commandHat, commandTotem};
        }
        return null;
    }
}
