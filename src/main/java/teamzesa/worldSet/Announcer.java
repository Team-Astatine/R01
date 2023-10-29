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
import teamzesa.dataValue.userData.UserHandler;
import teamzesa.dataValue.userData.UserMapHandler;

import java.awt.*;

import static teamzesa.ComponentExchanger.componentSet;

public class Announcer {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    private final ConfigIOHandler configIOHandler;
    private final ThreadPool threadPool;

    private Announcer() {
        this.configIOHandler = ConfigIOHandler.getConfigIOHandler();
        this.threadPool = ThreadPool.getThreadPool();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }

    public void countAnnouncer(Player player) {

        int joinCnt = UserMapHandler.getUserHandler().getUser(player).getJoinCount();
        player.sendMessage(
                ComponentExchanger.componentSet(configIOHandler.getWorldMotdConfig(),new Color(173,216,230))
                        .append(ComponentExchanger.componentSet(" " + joinCnt ,new Color(255,182,193)))
                        .append(ComponentExchanger.componentSet("번째 접속!",new Color(173,216,230)))
        );
    }

    public void playerTab(@NotNull Audience player) {
        player.sendPlayerListHeader(
                ComponentExchanger.componentSet(configIOHandler.getWorldMotdConfig(),new Color(139, 0, 255))
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
        this.threadPool.addSchedulingTask(task,delay,interval);
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
                ComponentExchanger.createLinkComponent(configIOHandler.getMineListVote(), mineListLink, voteColor),
                ComponentExchanger.createLinkComponent(configIOHandler.getDiscordInvite(), discordLink, discordColor),
                ComponentExchanger.createLinkComponent(configIOHandler.getServerGuideNotion(), notionLink, notionColor),
                ComponentExchanger.componentSet(configIOHandler.getSteelLifeTip(), commonColor),
                ComponentExchanger.componentSet(configIOHandler.getRaidTip(), commonColor),
                ComponentExchanger.componentSet(configIOHandler.getWeaponTip(), commonColor),
                ComponentExchanger.componentSet(configIOHandler.getExplosiveTip(), commonColor),
                ComponentExchanger.componentSet(configIOHandler.getCommandFly(), commandColor),
                ComponentExchanger.componentSet(configIOHandler.getCommandHat(), commandColor),
                ComponentExchanger.componentSet(configIOHandler.getCommandTotem(), commandColor)
        };
    }

}
