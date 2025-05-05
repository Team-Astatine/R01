package teamzesa.Event.Restricted;

import org.bukkit.event.server.TabCompleteEvent;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Util.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public class RestrictedCommandTabCompleteEvent extends StringComponentExchanger implements EventRegister {

    private final TabCompleteEvent event;

    public RestrictedCommandTabCompleteEvent(TabCompleteEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {

    }

    @Override
    public void execute() {
        List<String> restrictedCommand = new ArrayList<>();
        restrictedCommand.add("공지");
        event.setCompletions(restrictedCommand);

        event.setCancelled(true);

        System.out.println(event.isCommand());
        System.out.println(event.getBuffer());
        System.out.println(event.getEventName());
        System.out.println(event.getHandlers());
    }
}
