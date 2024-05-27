package teamzesa.util.IOHandler;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.entity.User;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.ThreadPool;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.userHandler.UserController;

import java.util.ArrayList;
import java.util.List;

public class Announcer extends StringComponentExchanger {

    private static class AnnouncerHolder {
        private static final Announcer INSTANCE = new Announcer();
    }

    public static Announcer getAnnouncer() {
        return AnnouncerHolder.INSTANCE;
    }


    private final ConfigIOHandler configIOHandler;

    private Announcer() {
        this.configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    public void countAnnouncer(Player player) {
        int joinCnt = new UserController().readUser(player).joinCount();
        player.sendMessage(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorMap.SKY_BLUE)
                        .append(componentExchanger(" " + joinCnt, ColorMap.PINK))
                        .append(componentExchanger("번째 접속!", ColorMap.SKY_BLUE))
        );
    }

    public void setPlayerTabHeader(@NotNull Audience audience) {
        audience.sendPlayerListHeader(
                componentExchanger(this.configIOHandler.getWorldMotdConfig(), ColorMap.PURPLE)
        );
    }

    public void joinAnnouncer(Player player) {
        Component[] components = createComponents();
        for (Component component : components)
            player.sendMessage(component);
    }

    public void defaultAnnouncer() {
        long delay = 0;
        long interval = 5;
        ThreadPool.getThreadPool().addSchedulingTaskMin(() -> {
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            if (players.isEmpty())
                return;

            players.forEach(player -> {
                User user = new UserController().readUser(player);
                if (user.isAnnouncing())
                    sendComment(player, createComponents());
            });
        },
        delay,
        interval
        );
    }

    private void sendComment(Player player, Component[] component) {
        for (Component commentList : component)
            player.sendMessage(commentList);
    }

    private Component @NotNull [] createComponents() {
        String notionLink = this.configIOHandler.getNotionConfig();
        String discordLink = this.configIOHandler.getDiscordConfig();
        String mineListLink = this.configIOHandler.getMineListConfig();

        ColorMap commonColor = ColorMap.RED;
        ColorMap voteColor = ColorMap.VOTE_COLOR;
        ColorMap notionColor = ColorMap.NOTIOIN_COLOR;
        ColorMap commandColor = ColorMap.COMMAND_COLOR;
        ColorMap discordColor = ColorMap.DISCORD_COLOR;
        ColorMap enhanceColor = ColorMap.PINK;

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
                componentExchanger(this.configIOHandler.getCommandTotem(), commandColor),
                componentExchanger(this.configIOHandler.getCommandEnhance(), enhanceColor),
                componentExchanger(this.configIOHandler.getCommandAnnouncing(), commandColor)
        };
    }

}
