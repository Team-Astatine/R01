package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import teamzesa.user.User;
import teamzesa.user.UserHandler;

import java.util.Map;
import java.util.UUID;

public class NameChanger implements CommandExecutor {
    private UserHandler userHandler;
    private Map<UUID, User> userData;

    public NameChanger() {
        userHandler = UserHandler.getInstance();
        userData = userHandler.getUserMap();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "권한이 없습니다.");
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "플레이어를 찾을 수 없습니다.");
            return true;
        }

        String newNickname = args[1];
        player.sendMessage(ChatColor.YELLOW + player.getName() + "님의 닉네임을 " + newNickname + "로 변경하였습니다.");

        ChatColor listNameColor = ChatColor.RESET;

        if (args[2].equals("moder"))
            listNameColor = ChatColor.RED;

        if (args[2].equals("user"))
            listNameColor = ChatColor.WHITE;

        player.setPlayerListName(listNameColor + newNickname);

        User user = userData.get(player.getUniqueId());
        user.setKoreanName(listNameColor + newNickname);

        return true;
    }
}
