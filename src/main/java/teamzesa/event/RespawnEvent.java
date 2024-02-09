package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;


public class RespawnEvent extends ComponentExchanger implements EventRegister {
    private final PlayerRespawnEvent event;

    public RespawnEvent(PlayerRespawnEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {

    }

    @Override
    public void execute() {
        deathRandomTeleport();
    }

    public void deathRandomTeleport() {
        Player player = this.event.getPlayer();

        if (player.getBedSpawnLocation() == null)
            playerSendMsgComponentExchanger(player, "침대가 없어 랜덤 텔레포트 되었습니다.", ColorList.ORANGE);
        else playerSendMsgComponentExchanger(player, "침대로 텔레포트 됩니다.", ColorList.YELLOW);
    }
}
