package teamzesa.command.ModeratorCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandType;


public class Motd extends CommandRegisterSection {

    private static final String DEFAULT_SERVER_MOTD = "Astatine Online";
    private String newMotd;

    public Motd() {
        super(CommandType.MOTD);
        this.newMotd = DEFAULT_SERVER_MOTD;
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        getteringNewMotd(args);
        sendComment(sender);
        configDataUpdate();
        return false;
    }

    private void getteringNewMotd(String @NotNull [] newMotd) {
        if (newMotd.length == 0) {
            this.newMotd = DEFAULT_SERVER_MOTD;
            return;
        }

        StringBuilder customMotd = new StringBuilder();
        for (String motd : newMotd)
            customMotd.append(motd).append(" ");

        this.newMotd = customMotd.toString().trim();
    }

    private void sendComment(CommandSender sender) {
        String comment = "New Motd Set -> " + this.newMotd;
        if (sender instanceof Player player) {
            playerSendMsgComponentExchanger(player, comment, ColorList.YELLOW);
        } else Bukkit.getLogger().info(comment);
    }

    private void configDataUpdate() {
        Bukkit.motd(componentExchanger(this.newMotd, ColorList.VOTE_COLOR));
        ConfigIOHandler.getConfigIOHandler().worldConfigSave(this.newMotd);
    }
}
