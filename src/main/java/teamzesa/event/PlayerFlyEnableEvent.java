package teamzesa.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;

public class PlayerFlyEnableEvent extends ComponentExchanger implements EventRegister {
    private Player player;
    private String comment;

    private Event event;
    private PlayerJoinEvent joinEvent;
    private PlayerDeathEvent deathEvent;
    private PlayerChangedWorldEvent worldChangeEvent;

    public PlayerFlyEnableEvent(Event event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        if (event instanceof PlayerJoinEvent joinEvent) {
            this.joinEvent = joinEvent;
            this.player = joinEvent.getPlayer();
            this.comment = "서버에 접속하여 플라이가 활성화 됩니다";
        }
        else if (event instanceof PlayerDeathEvent deathEvent) {
            this.deathEvent = deathEvent;
            this.player = deathEvent.getPlayer();
            this.comment = "사망하여 플라이가 재활성화 됩니다";
        }
        else if (event instanceof PlayerChangedWorldEvent worldChangeEvent) {
            this.worldChangeEvent = worldChangeEvent;
            this.player = worldChangeEvent.getPlayer();
            this.comment = "월드를 이동하여 플라이가 재활성화 됩니다";
        }
    }

    @Override
    public void execute() {
        this.player.setAllowFlight(true);
        playerSendMsgComponentExchanger(this.player, this.comment, ColorList.YELLOW);
    }
}
