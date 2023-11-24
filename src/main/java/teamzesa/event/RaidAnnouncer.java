package teamzesa.event;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.WorldName;

public class RaidAnnouncer extends ComponentExchanger implements Listener {
    @EventHandler
    public synchronized void raidAnnouncer(@NotNull RaidTriggerEvent e) {
        Location loc = e.getRaid().getLocation();
        String activeWorld = e.getWorld().getName();
        String activeWorldKorean = WorldName.valueOf(activeWorld).getKoreanWorldName();

        double x = loc.getX();
        double z = loc.getZ();

        sendAnnouncerComponentExchanger(
                String.format("%s 월드의 X : %1.0f | Z : %1.0f 에서 레이드가 시작됩니다.", activeWorldKorean, x, z),
                ColorList.ORANGE);
    }
}
