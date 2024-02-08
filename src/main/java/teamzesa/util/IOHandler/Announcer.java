package teamzesa.util.IOHandler;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
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

    public void countAnnouncer(Player player) {
        int joinCnt = new UserController().readUser(player).joinCount();
        player.sendMessage(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorList.SKY_BLUE)
                        .append(componentExchanger(" " + joinCnt, ColorList.PINK))
                        .append(componentExchanger("번째 접속!", ColorList.SKY_BLUE))
        );
    }

    public void setPlayerTabHeader(@NotNull Audience audience) {
        audience.sendPlayerListHeader(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorList.PURPLE)
        );
    }

    public void joinAnnouncer(Player player) {
        Component[] components = createComponents();
        for (Component component : components)
            player.sendMessage(component);
    }

    public void defaultAnnouncer() {
        long delay = 0;
        long interval = 5; //minutes

        Runnable commentSendTask = () -> {
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            if (players.isEmpty())
                return;

            players.forEach(player -> sendComment(player, createComponents()));
        };

        ThreadPool.getThreadPool().addSchedulingTask(commentSendTask, delay, interval);
    }

    private void sendComment(Player player, Component[] component) {
        for (Component commentList : component)
            player.sendMessage(commentList);
    }

    private Component @NotNull [] createComponents() {
        String notionLink = this.configIOHandler.getNotionConfig();
        String discordLink = this.configIOHandler.getDiscordConfig();
        String mineListLink = this.configIOHandler.getMineListConfig();

        ColorList commonColor = ColorList.RED;
        ColorList voteColor = ColorList.VOTE_COLOR;
        ColorList notionColor = ColorList.NOTIOIN_COLOR;
        ColorList commandColor = ColorList.COMMAND_COLOR;
        ColorList discordColor = ColorList.DISCORD_COLOR;

        return new Component[]{
                createLinkComponentExchanger(this.configIOHandler.getMineListVote(), mineListLink, voteColor),
                createLinkComponentExchanger(this.configIOHandler.getDiscordInvite(), discordLink, discordColor),
                createLinkComponentExchanger(this.configIOHandler.getServerGuideNotion(), notionLink, notionColor),

                componentExchanger(this.configIOHandler.getSteelLifeTip(), commonColor),
                componentExchanger(this.configIOHandler.getRaidTip(), commonColor),
                componentExchanger(this.configIOHandler.getWeaponTip(), commonColor),
                componentExchanger(this.configIOHandler.getExplosiveTip(), commonColor),
                componentExchanger(this.configIOHandler.getCommandFly(), commandColor),
                componentExchanger(this.configIOHandler.getCommandHat(), commandColor),
                componentExchanger(this.configIOHandler.getCommandTotem(), commandColor)
        };
    }

}
