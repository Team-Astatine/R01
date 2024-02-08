package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.entity.User;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.userHandler.UserController;

public class QuitEvent extends ComponentExchanger implements EventRegister {
    private final Announcer announcer = Announcer.getAnnouncer();
    private final UserController userController = new UserController();
    private User quitUser;
    private Player quitPlayer;
    private final PlayerQuitEvent quitEvent;

    public QuitEvent(PlayerQuitEvent event) {
        this.quitEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.quitPlayer = this.quitEvent.getPlayer();
        this.quitUser = this.userController.readUser(this.quitEvent.getPlayer());
    }

    @Override
    public void execute() {
        if (this.quitUser.killStatus() == 0)
            this.quitEvent.quitMessage(
                    componentExchanger("- " + this.quitUser.name(), ColorList.RED)
            );

        else
            this.quitEvent.quitMessage(
                    componentExchanger("- " + this.quitUser.name() + " " + quitUser.killStatus() + "KILL", ColorList.RED)
            );
    }
}
