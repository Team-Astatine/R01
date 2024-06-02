package teamzesa.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import teamzesa.DataBase.entity.User;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;
import teamzesa.DataBase.userHandler.UserController;

public class PlayerFlyEnableEvent extends StringComponentExchanger implements EventRegister {
    private Player player;
    private String comment;
    private final Event event;

    public PlayerFlyEnableEvent(Event event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        if (event instanceof PlayerJoinEvent joinEvent) {
            this.player = joinEvent.getPlayer();
            this.comment = "서버에 접속하여 플라이가 활성화 됩니다";
        }
        else if (event instanceof PlayerDeathEvent deathEvent) {
            this.player = deathEvent.getPlayer();
            this.comment = "사망하여 플라이가 재활성화 됩니다";
        }
        else if (event instanceof PlayerChangedWorldEvent worldChangeEvent) {
            this.player = worldChangeEvent.getPlayer();
            this.comment = "월드를 이동하여 플라이가 재활성화 됩니다";
        }
    }

    @Override
    public void execute() {
        this.player.setAllowFlight(true);
        User user = new UserController().readUser(this.player.getUniqueId());
        if (!user.isGodMode()) playerSendMsgComponentExchanger(this.player, this.comment, ColorMap.YELLOW);
    }
}
