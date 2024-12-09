package teamzesa.event.PlayerQuitEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.UserHandler.UserController;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Interface.StringComponentExchanger;

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
                    componentExchanger(" - " + this.quitUser.nameList().getLast(), ColorList.RED)
            );

        else
            this.quitEvent.quitMessage(
                    componentExchanger(" - [ " + userKillStatus.killCount() + "KILL ] " + this.quitUser.nameList().getLast(), ColorList.RED)
            );
    }
}