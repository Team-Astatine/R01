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
        for (Component component : serverTip)
            Bukkit.broadcast(component);
    }

    public void defaultAnnouncer() {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents(1);
        Bukkit.getScheduler().runTaskTimer(R01.getPlugin(R01.class), () -> {
            for (Component component : components)
                Bukkit.broadcast(component);
        }, delay, interval);
    }

    public void commandAnnouncer() {
        int delay = 0; // 초기 딜레이 (0 틱, 즉 즉시 시작)
        int interval = 18000; // 3분마다 (1초 = 20틱)

        Component[] components = createComponents(2);
        Bukkit.getScheduler().runTaskTimer(R01.getPlugin(R01.class), () -> {
            for (Component component : components)
                Bukkit.broadcast(component);
        }, delay, interval);
    }

    private Component[] createComponents(int menu) {
        String mineListLink = configIOHandler.getMineListConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String notionLink = configIOHandler.getNotionConfig();

        if (menu == 1) {
            Component mineList = createLinkComponent(configIOHandler.getMineListVote(), mineListLink, new Color(167, 123, 202));
            Component discord = createLinkComponent(configIOHandler.getDiscordInvite(), discordLink, new Color(114, 137, 218));
            Component notion = createLinkComponent(configIOHandler.getServerGuideNotion(), notionLink, new Color(112, 71, 157));
            return new Component[]{mineList, discord, notion};
        }

        if (menu == 2) {
            Component steelLifeTip = componentSet(configIOHandler.getSteelLifeTip(), new Color(233, 30, 99));
            Component raidTip = componentSet(configIOHandler.getRaidTip(), new Color(233, 30, 99));
            Component weaponTip = componentSet(configIOHandler.getWeaponTip(), new Color(233, 30, 99));
            Component explosiveTip = componentSet(configIOHandler.getExplosiveTip(), new Color(233, 30, 99));
            Component commandFly = componentSet(configIOHandler.getCommandFly(), new Color(139, 195, 74));
            Component commandHat = componentSet(configIOHandler.getCommandHat(), new Color(139, 195, 74));
            Component commandTotem = componentSet(configIOHandler.getCommandTotem(), new Color(139, 195, 74));
            return new Component[]{steelLifeTip, raidTip, weaponTip, explosiveTip, commandFly, commandHat, commandTotem};
        }
        return null;
    }
}
