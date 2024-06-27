package teamzesa.event.Restricted;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;

import java.util.HashSet;
import java.util.Set;

public class BanCommandHandler extends StringComponentExchanger implements EventRegister {
    private Player commandSender;
    private String currentCommand;
    private Set<String> lockingCommand;
    private final PlayerCommandPreprocessEvent event;
    public BanCommandHandler(PlayerCommandPreprocessEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.commandSender = this.event.getPlayer();
        this.currentCommand = this.event.getMessage();

        this.lockingCommand = new HashSet<>();
        this.lockingCommand.add("/pl");
        this.lockingCommand.add("/plugins");
    }

    @Override
    public void execute() {
        boolean messageTypeCheck = this.lockingCommand.stream()
                .anyMatch(banningCommand -> banningCommand.equalsIgnoreCase(this.currentCommand));

        if (messageTypeCheck && !this.commandSender.isOp()) {
            playerSendMsgComponentExchanger(
                    this.commandSender, "해당 명령어는 사용불가합니다.", ColorMap.RED);
            this.event.setCancelled(true);
        }
    }
}
