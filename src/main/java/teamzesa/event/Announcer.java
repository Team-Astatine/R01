package teamzesa.event;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.IOHandler.ConfigIOHandler;
import teamzesa.util.ThreadPool;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserController;

import java.util.ArrayList;
import java.util.List;

public class Announcer extends ComponentExchanger {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    private final ConfigIOHandler configIOHandler;

    private Announcer() {
        this.configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }

    public void joinKillStatusAnnouncer(User user) {
        if (user.getKillStatus() == 0)
            return;

        Bukkit.broadcast(
                componentExchanger(user.getKillStatus() + "킬 " + user.getName() + "님 접속!",ColorList.RED)
        );
    }

    public void countAnnouncer(Player player) {
        int joinCnt = new UserController().readUser(player).getJoinCount();
        player.sendMessage(
                componentExchanger(configIOHandler.getWorldMotdConfig(),ColorList.SKY_BLUE)
                        .append(componentExchanger(" " + joinCnt ,ColorList.PINK))
                        .append(componentExchanger("번째 접속!",ColorList.SKY_BLUE))
        );
    }

    public void setPlayerTabHeader(@NotNull Audience audience) {
        audience.sendPlayerListHeader(
                componentExchanger(configIOHandler.getWorldMotdConfig(),ColorList.PURPLE)
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

        Runnable commentSendTask = () -> {
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            if (players.isEmpty())
                return;

            players.forEach(player -> sendComment(player,createComponents()));
        };

        ThreadPool.getThreadPool().addSchedulingTask(commentSendTask,delay,interval);
    }

    private void sendComment(Player player, Component[] component) {
        for (Component commentList : component)
            player.sendMessage(commentList);
    }

    private Component @NotNull [] createComponents() {
        String notionLink = configIOHandler.getNotionConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String mineListLink = configIOHandler.getMineListConfig();

        ColorList commonColor = ColorList.RED;
        ColorList voteColor = ColorList.VOTE_COLOR;
        ColorList notionColor = ColorList.NOTIOIN_COLOR;
        ColorList commandColor = ColorList.COMMAND_COLOR;
        ColorList discordColor = ColorList.DISCORD_COLOR;

        return new Component[] {
                createLinkComponentExchanger(configIOHandler.getMineListVote(), mineListLink, voteColor),
                createLinkComponentExchanger(configIOHandler.getDiscordInvite(), discordLink, discordColor),
                createLinkComponentExchanger(configIOHandler.getServerGuideNotion(), notionLink, notionColor),

                componentExchanger(configIOHandler.getSteelLifeTip(), commonColor),
                componentExchanger(configIOHandler.getRaidTip(), commonColor),
                componentExchanger(configIOHandler.getWeaponTip(), commonColor),
                componentExchanger(configIOHandler.getExplosiveTip(), commonColor),
                componentExchanger(configIOHandler.getCommandFly(), commandColor),
                componentExchanger(configIOHandler.getCommandHat(), commandColor),
                componentExchanger(configIOHandler.getCommandTotem(), commandColor)
        };
    }

}
