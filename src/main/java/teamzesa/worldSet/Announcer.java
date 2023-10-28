package teamzesa.worldSet;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.ThreadPool;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;

public class Announcer extends ComponentExchanger {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    private final ConfigIOHandler configIOHandler;
    private final UserMapHandler userMapHandler;
    private final ThreadPool threadPool;

    private Announcer() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
        userMapHandler = UserMapHandler.getUserHandler();
        threadPool = ThreadPool.getThreadPool();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }

    public void countAnnouncer(Player player) {
        int joinCnt = userMapHandler.getUser(player).getJoinCount();
        player.sendMessage(
                ComponentExchanger.componentSet(configIOHandler.getWorldMotdConfig(),new Color(173,216,230))
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
        Component[] components = createComponents();
        for (Component component : components)
            player.sendMessage(component);
    }

    public void defaultAnnouncer() {
        long delay = 0;
        long interval = 3600; // 3분마다 (1초 = 20틱)
//        long interval = 1; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents();

        Runnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Component component : components)
                    Bukkit.broadcast(component);
            }
        };
        threadPool.addSchedulingTask(task,delay,interval);
    }

    private Component[] createComponents() {
        String mineListLink = configIOHandler.getMineListConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String notionLink = configIOHandler.getNotionConfig();

        Color voteColor = new Color(167, 123, 202);
        Color commonColor = new Color(233, 30, 99);
        Color notionColor = new Color(112, 71, 157);
        Color commandColor = new Color(139, 195, 74);
        Color discordColor = new Color(114, 137, 218);

        return new Component[]{
                createLinkComponent(configIOHandler.getMineListVote(), mineListLink, voteColor),
                createLinkComponent(configIOHandler.getDiscordInvite(), discordLink, discordColor),
                createLinkComponent(configIOHandler.getServerGuideNotion(), notionLink, notionColor),
                componentSet(configIOHandler.getSteelLifeTip(), commonColor),
                componentSet(configIOHandler.getRaidTip(), commonColor),
                componentSet(configIOHandler.getWeaponTip(), commonColor),
                componentSet(configIOHandler.getExplosiveTip(), commonColor),
                componentSet(configIOHandler.getCommandFly(), commandColor),
                componentSet(configIOHandler.getCommandHat(), commandColor),
                componentSet(configIOHandler.getCommandTotem(), commandColor)
        };
    }

}
