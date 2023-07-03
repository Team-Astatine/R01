package teamzesa.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import teamzesa.announcer.ComponentExchanger;
import teamzesa.userValue.UserHandler;

public class NameChanger implements CommandExecutor {
    private UserHandler userHandler;

    public NameChanger() {
        userHandler = UserHandler.getUserHandler();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            ComponentExchanger.playerAnnouncer(
                    (Player)sender,"플레이어를 찾을 수 없습니다.", TextColor.color(0xF80040));
            return true;
        }

        ComponentExchanger.playerAnnouncer(
                player, player.getName() + "님의 닉네임을 " + args[1] + "로 변경하였습니다."
                        ,TextColor.color(255,255,0)
        );

        TextColor nameColor = null;

        if (args[2].equals("moder"))
            nameColor = TextColor.color(0xF89935);

        if (args[2].equals("user"))
            nameColor = TextColor.color(255,255,255);

        Component listName = ComponentExchanger.componentSet(args[1], nameColor);

        player.customName(listName);
        player.playerListName(listName);
        setPlayerNameTag(player, listName);

        return true;
    }

    public void setPlayerNameTag(Player player, Component component) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getEntryTeam(player.getName());

        if (team == null)
            team = scoreboard.registerNewTeam(player.getName());

        team.prefix(component);
        team.addEntry(" " + player.getName());
    }
}