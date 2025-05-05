package teamzesa.Event.PlayerQuitEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.Data.User.UserKillStatus.KillStatusController;
import teamzesa.Data.User.UserData.User;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;
import teamzesa.Data.User.UserData.UserController;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.StringComponentExchanger;

public class QuitMsgEvent extends StringComponentExchanger implements EventRegister {
    private User quitUser;
    private final PlayerQuitEvent quitEvent;

    public QuitMsgEvent(PlayerQuitEvent event) {
        this.quitEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        UserController userController = new UserController();
        this.quitUser = userController.readUser(this.quitEvent.getPlayer().getUniqueId());
    }

    @Override
    public void execute() {
        UserKillStatus userKillStatus = new KillStatusController().readUser(this.quitUser.uuid());

        if (userKillStatus.killCount() == 0)
            this.quitEvent.quitMessage(
                    componentExchanger(" - " + this.quitUser.nameList().getLast(), ColorType.RED)
            );

        else
            this.quitEvent.quitMessage(
                    componentExchanger(" - [ " + userKillStatus.killCount() + "KILL ] " + this.quitUser.nameList().getLast(), ColorType.RED)
            );
    }
}