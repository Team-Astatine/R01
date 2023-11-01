package teamzesa.worldSet;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;

public class RaidAnnouncer implements Listener {
    @EventHandler
    public void raidAnnouncer(@NotNull RaidTriggerEvent e) {
        Location loc = e.getRaid().getLocation();
        String activeWorldName = WorldName.valueOf(e.getWorld().getName()).getWorldName();

        double x = loc.getX();
        double z = loc.getZ();

        ComponentExchanger.serverAnnouncer(
                String.format("%s 월드의 X : %1.0f | Z : %1.0f 에서 레이드가 시작됩니다.", activeWorldName, x, z),
                ColorList.ORANGE);
    }
}
