package teamzesa.event.RaidTriggerEvent;

import org.bukkit.Location;
import org.bukkit.event.raid.RaidTriggerEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.WorldName;
import teamzesa.util.Interface.StringComponentExchanger;

public class RaidAnnouncerEvent extends StringComponentExchanger implements EventRegister {
    private Location location;
    private String activeWorld;
    private final RaidTriggerEvent event;

    public RaidAnnouncerEvent(RaidTriggerEvent event) {
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
        WorldName activeWorld = WorldName.findByWorldName(this.activeWorld);

        double x = this.location.getX();
        double z = this.location.getZ();

        sendAnnouncerComponentExchanger(
                String.format("%s 월드의 X : %1.0f | Z : %1.0f 에서 레이드가 시작됩니다.",
                        activeWorld.getKoreanWorldName(), x, z), ColorList.ORANGE);
    }
}
