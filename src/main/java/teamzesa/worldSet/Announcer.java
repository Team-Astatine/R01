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
        int interval = 3 * 60 * 20; // 5분마다 (1분 = 60초, 1초 = 20틱)

        String mineListLink = configIOHandler.minelistConfigLoad();
        String discordLink = configIOHandler.discordConfigLoad();
        String notionLink = configIOHandler.notionConfigLoad();

        Component mineList = createLinkComponent("마인리스트 추천",mineListLink,Color.GREEN);
        Component discord = createLinkComponent("디스코드 참가",discordLink,Color.BLUE);
        Component notion = createLinkComponent("서버 플레이 방법",notionLink,Color.MAGENTA);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Bukkit.broadcast(mineList);
            Bukkit.broadcast(discord);
            Bukkit.broadcast(notion);
        }, delay, interval);
    }
}
