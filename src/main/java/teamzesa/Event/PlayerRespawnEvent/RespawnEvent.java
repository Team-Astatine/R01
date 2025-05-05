package teamzesa.Event.PlayerRespawnEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.StringComponentExchanger;


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
        if (ObjectUtils.isEmpty(this.player.getPotentialBedLocation()))
            return;

        String comment = this.event.isAnchorSpawn() ? "정박기로 텔레포트 합니다" : "침대로 텔레포트 합니다";

        playerSendMsgComponentExchanger(this.player, comment, ColorType.YELLOW);
    }
}
