package teamzesa.event.PlayerJoinEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Interface.StringComponentExchanger;

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
        switch (event) {
            case PlayerJoinEvent e -> {
                this.player = e.getPlayer();
                this.comment = "서버에 접속하여 플라이가 활성화 됩니다";
            }

            case PlayerRespawnEvent e -> {
                if (e.getRespawnReason().equals(PlayerRespawnEvent.RespawnReason.END_PORTAL))
                    return;
                this.player = e.getPlayer();
                this.comment = "리스폰하여 플라이가 재활성화 됩니다";
            }

            case PlayerChangedWorldEvent e -> {
                this.player = e.getPlayer();
                this.comment = "월드를 이동하여 플라이가 재활성화 됩니다";
            }

            default -> throw new IllegalStateException("Unknown Event value: " + event);
        }
    }

    @Override
    public void execute() {
        if (this.player == null)
            return;

        this.player.setAllowFlight(true);
        this.player.setFlying(true);

        User user = new UserController().readUser(this.player.getUniqueId());
        if (user.isGodMode())
            return;

        playerSendMsgComponentExchanger(this.player, this.comment, ColorMap.YELLOW);
    }
}
