package teamzesa.Event.PlayerDeathEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.Data.User.UserData.UserController;
import teamzesa.Data.User.UserKillStatus.KillStatusBuilder;
import teamzesa.Data.User.UserKillStatus.KillStatusController;
import teamzesa.Data.User.UserKillStatus.UserKillStatus;
import teamzesa.Event.EventRegister.EventRegister;

public class UpdateKillStatusForKiller implements EventRegister {
    private KillStatusController controller = new KillStatusController();
    private UserController userController = new UserController();

    private UserKillStatus userKillStatus;
    private final PlayerDeathEvent event;

    public UpdateKillStatusForKiller(PlayerDeathEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        Player player = this.event.getPlayer().getKiller();

        if (ObjectUtils.isEmpty(player))
            return;

        if (this.userController.readUser(player.getUniqueId()).godMode())
            return;

        this.userKillStatus = this.controller.readUser(player.getUniqueId());
        new KillStatusBuilder(this.userKillStatus)
                .killCount(this.userKillStatus.killCount() + 1)
                .buildAndUpdate();
    }
}
