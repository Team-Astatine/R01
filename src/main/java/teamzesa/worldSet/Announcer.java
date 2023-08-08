package teamzesa.worldSet;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.config.ConfigIOHandler;

import java.awt.*;

public class Announcer extends ComponentExchanger {

    private static class AnnouncerHolder {
        private static Announcer INSTANCE = new Announcer();
    }
    ConfigIOHandler configIOHandler;

    private Announcer() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }

    private Component createLinkComponent(String text, String url, Color color) {
        TextComponent.Builder component = Component.text()
                .content(text)
                .color(TextColor.color(color.getRGB() & 0xFFFFFF))
                .clickEvent(ClickEvent.openUrl(url));
        return component.build();
    }

    public void defaultAnnouncer(JavaPlugin plugin) {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        String mineListLink = configIOHandler.mineListConfigLoad();
        String discordLink = configIOHandler.discordConfigLoad();
        String notionLink = configIOHandler.notionConfigLoad();

        Component firstLine = componentSet("================[클릭하세요]================");
        Component mineList = createLinkComponent("[클릭] > 마인리스트 추천",mineListLink,Color.GREEN);
        Component discord = createLinkComponent("[클릭] > 디스코드 참가",discordLink,new Color(114, 137, 218));
        Component notion = createLinkComponent("[클릭] > 서버 플레이 가이드",notionLink,Color.MAGENTA);
        Component secondLine = componentSet("========================================");

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Bukkit.broadcast(firstLine);
            Bukkit.broadcast(mineList);
            Bukkit.broadcast(discord);
            Bukkit.broadcast(notion);
            Bukkit.broadcast(secondLine);
        }, delay, interval);
    }
}
