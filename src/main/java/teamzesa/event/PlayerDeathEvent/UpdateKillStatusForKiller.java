package teamzesa.event.PlayerDeathEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusBuilder;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.event.EventRegister.EventRegister;

public class UpdateKillStatusForKiller implements EventRegister {
    private KillStatusController controller = new KillStatusController();

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

        this.userKillStatus = this.controller.readUser(player.getUniqueId());
        new KillStatusBuilder(this.userKillStatus)
                .killCount(this.userKillStatus.killCount() + 1)
                .buildAndUpdate();
    }
}
