package teamzesa.command.Announcing;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.ArrayList;

public class Community extends CommandRegisterSection {

    public Community() {
        super(CommandExecutorMap.COMMUNITY);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();

        String notionLink = configIOHandler.getNotionConfig();
        String discordLink = configIOHandler.getDiscordConfig();
        String mineListLink = configIOHandler.getMineListConfig();

        ColorList voteColor = ColorList.GREEN;
        ColorList notionColor = ColorList.NOTION_COLOR;
        ColorList discordColor = ColorList.DISCORD_COLOR;

        ArrayList<Component> communityLink = new ArrayList<>();
        communityLink.add(createLinkComponentExchanger(configIOHandler.getMineListVote(), mineListLink, voteColor));
        communityLink.add(createLinkComponentExchanger(configIOHandler.getDiscordInvite(), discordLink, discordColor));
        communityLink.add(createLinkComponentExchanger(configIOHandler.getServerGuideNotion(), notionLink, notionColor));

        for (Component comment : communityLink)
            playerSendMsgComponentExchanger((Player) commandSender, comment);

        return true;
    }
}
