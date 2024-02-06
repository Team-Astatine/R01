package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.WorldName;

public class RaidAnnouncer extends ComponentExchanger implements EventRegister {
    private Location location;
    private String activeWorld;
    private final RaidTriggerEvent event;

    public RaidAnnouncer(RaidTriggerEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.location = this.event.getRaid().getLocation();
        this.activeWorld = this.event.getWorld().getName();
    }

    @Override
    public void execute() {
        String activeWorldKorean = WorldName.valueOf(activeWorld).getKoreanWorldName();

        double x = this.location.getX();
        double z = this.location.getZ();

        sendAnnouncerComponentExchanger(
                String.format("%s 월드의 X : %1.0f | Z : %1.0f 에서 레이드가 시작됩니다.", activeWorldKorean, x, z),
                ColorList.ORANGE);
    }
}
