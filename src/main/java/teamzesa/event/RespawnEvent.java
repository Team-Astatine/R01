package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;


public class RespawnEvent extends StringComponentExchanger implements EventRegister {
    private Player player;
    private final PlayerRespawnEvent event;

    public RespawnEvent(PlayerRespawnEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
    }

    @Override
    public void execute() {
        deathRandomTeleport();
    }

    public void deathRandomTeleport() {
        if (this.player.getPotentialBedLocation() == null)
            playerSendMsgComponentExchanger(this.player, "침대가 없어 랜덤 텔레포트 되었습니다", ColorMap.ORANGE);
        else playerSendMsgComponentExchanger(this.player, "침대로 텔레포트 됩니다", ColorMap.YELLOW);
    }
}
