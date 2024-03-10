package teamzesa.event;

import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.entity.User;
import teamzesa.util.Interface.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserController;

public class QuitMsgEvent extends StringComponentExchanger implements EventRegister {
    private User quitUser;
    private UserController userController;
    private final PlayerQuitEvent quitEvent;

    public QuitMsgEvent(PlayerQuitEvent event) {
        this.quitEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.userController = new UserController();
        this.quitUser = this.userController.readUser(this.quitEvent.getPlayer());
    }

    @Override
    public void execute() {
        if (this.quitUser.killStatus() == 0)
            this.quitEvent.quitMessage(
                    componentExchanger(" - " + this.quitUser.name(), ColorList.RED)
            );

        else
            this.quitEvent.quitMessage(
                    componentExchanger(" - [ " + this.quitUser.killStatus() + "KILL ] " + this.quitUser.name(), ColorList.RED)
            );
    }
}
