package teamzesa.event.Restricted;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.Enum.ColorList;
import teamzesa.util.Interface.StringComponentExchanger;

public class RestrictedCommandHandler extends StringComponentExchanger implements EventRegister {
    private Player commandSender;
    private String currentCommand;

    private final PlayerCommandPreprocessEvent event;

    public RestrictedCommandHandler(PlayerCommandPreprocessEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.commandSender = this.event.getPlayer();
        this.currentCommand = this.event.getMessage();
    }

    @Override
    public void execute() {
        if (this.commandSender.isOp())
            return;

        boolean commandAllowed = new RestrictedElement().restrictedCommand.stream()
                .anyMatch(this.currentCommand::equals);

        if (BooleanUtils.isFalse(commandAllowed))
            return;

        this.event.setCancelled(true);
        playerSendMsgComponentExchanger(this.commandSender, "해당 명령어는 사용불가합니다.", ColorList.RED);
    }
}
