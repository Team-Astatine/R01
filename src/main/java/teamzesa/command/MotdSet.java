package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.dataValue.ColorList;
import teamzesa.resgisterEvent.EventExecutor;


public class MotdSet implements CommandExecutor, EventExecutor {

    private String newMotd;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        setNewMotdToFieldVariable(args);
        sendComment(sender);
        configDataUpdate();
        return false;
    }

    private void setNewMotdToFieldVariable(String @NotNull [] newMotd) {
        StringBuilder customMotd = new StringBuilder();
        for (String motd : newMotd)
            customMotd.append(motd).append(" ");

        this.newMotd = customMotd.toString().trim();
    }

    private void sendComment(CommandSender sender) {
        String comment = "New Motd Set > " + this.newMotd;
        if (sender instanceof Player) {
            ComponentExchanger.playerAnnouncer((Player)sender, comment, ColorList.YELLOW);
        } else Bukkit.getLogger().info(comment);
    }

    private void configDataUpdate() {
        Bukkit.motd(ComponentExchanger.componentSet(this.newMotd));
        ConfigIOHandler.getConfigIOHandler().worldConfigSave(this.newMotd);
    }
}
