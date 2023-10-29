package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.resgisterEvent.EventExecutor;

import java.awt.*;

public class MotdSet implements CommandExecutor, EventExecutor {

    private ConfigIOHandler configIOHandler;

    public MotdSet() {
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    private String finalMotd;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String[] commandContext = args;

        if (!(sender instanceof Player)) {
            motdSetup(commandContext);
            Bukkit.getLogger().info("Motd Set > " + finalMotd);
            return true;
        }

        else if (sender.getName().equals("JAXPLE")) {
            motdSetup(commandContext);
            ComponentExchanger.playerAnnouncer((Player)sender, this.finalMotd + "로 변경 됐습니다.",Color.YELLOW);
            return true;
        }

        else ComponentExchanger.playerAnnouncer((Player)sender, "해당 명령어는 플레이어가 사용할 수 없습니다.", Color.RED);
        return false;
    }

    private void motdSetup(String[] commandContext) {
        StringBuilder customMotd = new StringBuilder();
        for (String motd : commandContext)
            customMotd.append(motd).append(" ");

        this.finalMotd = customMotd.toString().trim();
        applyMotd(this.finalMotd);
    }

    private void applyMotd(String finalMotd) {
        this.configIOHandler.worldConfigSave(finalMotd);
        Bukkit.motd(ComponentExchanger.componentSet(finalMotd));
    }
}
