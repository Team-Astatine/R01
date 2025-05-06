package teamzesa.Event.PlayerInteraction.Announce.JoinAndQuitMessage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import teamzesa.Data.User.UserKillStatus.KillStatusController;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.EventRegister;
import teamzesa.Event.PlayerInteraction.Announce.Tip.Announcer;
import teamzesa.Util.Function.StringComponentExchanger;

public class JoinMessageHandler extends StringComponentExchanger implements EventRegister {
    private Player joinPlayer;
    private UserKillStatus userKillStatus;

    private final PlayerJoinEvent event;

    public JoinMessageHandler(PlayerJoinEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.joinPlayer = this.event.getPlayer();
        this.userKillStatus = new KillStatusController().readUser(this.joinPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        this.joinPlayer.performCommand("help");
        Announcer.getAnnouncer().joinPlayerInformationAnnouncer(this.joinPlayer);

        if (this.userKillStatus.killCount() == 0)
            this.event.joinMessage(
                    componentExchanger(" + " + this.joinPlayer.getName(), ColorType.RED)
            );

        else this.event.joinMessage(
                componentExchanger(" + [ " + this.userKillStatus.killCount() + "KILL ] " + this.joinPlayer.getName(), ColorType.RED)
        );
    }
}