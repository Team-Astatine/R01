package teamzesa.Event.PlayerInteraction.Announce.JoinAndQuitMessage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.Data.User.UserKillStatus.UserKillStatusController;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;
import teamzesa.Event.EventRegister;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.Function.StringComponentExchanger;

public class QuitMessageHandler extends StringComponentExchanger implements EventRegister {
    private Player player;
    private UserKillStatus userKillStatus;

    private final PlayerQuitEvent event;

    public QuitMessageHandler(PlayerQuitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.userKillStatus = new UserKillStatusController().readUser(this.player.getUniqueId());
    }

    @Override
    public void execute() {
        if (this.userKillStatus.killCount() == 0)
            this.event.quitMessage(
                    componentExchanger(" - " + this.player.getName(), ColorType.RED)
            );

        else
            this.event.quitMessage(
                    componentExchanger(" - [ " + this.userKillStatus.killCount() + "KILL ] " + this.player.getName(), ColorType.RED)
            );
    }
}