package teamzesa.worldSet;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.R01;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;
import java.util.concurrent.ThreadPoolExecutor;

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
                componentSet(configIOHandler.getWorldMotdConfig(),new Color(173,216,230))
                        .append(componentSet(" " + joinCnt ,new Color(255,182,193)))
                        .append(componentSet("번째 접속!",new Color(173,216,230)))
        );
    }

    public void playerTab(@NotNull Audience player) {
        player.sendPlayerListHeader(
                componentSet(configIOHandler.getWorldMotdConfig(),new Color(139, 0, 255))
        );
    }

    public void joinAnnouncer(Player player) {
        Component[] components = createComponents(1);
        for (Component component : components)
            player.sendMessage(component);

        Component[] serverTip = createComponents(2);
        for (int i = 0; i < serverTip.length; i++)
            player.sendMessage(serverTip[i]);
    }

    public void defaultAnnouncer(int menu) {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents(menu);
        Bukkit.getScheduler().runTaskTimer(R01.getPlugin(R01.class), () -> {
            for (Component component : components)
                Bukkit.broadcast(component);
        }, delay, interval);
    }

    private Component[] createComponents(int menu) {
        String mineListLink = configIOHandler.getMineListConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String notionLink = configIOHandler.getNotionConfig();

        return switch (menu) {
            case 1 -> new Component[]{
                    createLinkComponent(configIOHandler.getMineListVote(), mineListLink, new Color(167, 123, 202)),
                    createLinkComponent(configIOHandler.getDiscordInvite(), discordLink, new Color(114, 137, 218)),
                    createLinkComponent(configIOHandler.getServerGuideNotion(), notionLink, new Color(112, 71, 157))
            };
            case 2 -> new Component[]{
                    componentSet(configIOHandler.getSteelLifeTip(), new Color(233, 30, 99)),
                    componentSet(configIOHandler.getRaidTip(), new Color(233, 30, 99)),
                    componentSet(configIOHandler.getWeaponTip(), new Color(233, 30, 99)),
                    componentSet(configIOHandler.getExplosiveTip(), new Color(233, 30, 99)),
                    componentSet(configIOHandler.getCommandFly(), new Color(139, 195, 74)),
                    componentSet(configIOHandler.getCommandHat(), new Color(139, 195, 74)),
                    componentSet(configIOHandler.getCommandTotem(), new Color(139, 195, 74))
            };
            default -> null;
        };
    }

}
