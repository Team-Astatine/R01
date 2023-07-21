package teamzesa.worldSet;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;
import teamzesa.ComponentExchanger;

import java.awt.*;


public class RaidAnnouncer extends ComponentExchanger implements Listener {
    @EventHandler
    public void raidAnnouncer(RaidTriggerEvent e) {
        WorldName activeWorldName = WorldName.valueOf(e.getWorld().getName());
        Location loc = e.getRaid().getLocation();

        double x = loc.getX();
        double z = loc.getZ();


        serverAnnouncer(
                String.format("%s 월드의 X : %1.0f | Z : %1.0f 에서 레이드가 시작됩니다.\n"
                , activeWorldName.getWorld(), x, z), Color.RED
        );
    }
}
