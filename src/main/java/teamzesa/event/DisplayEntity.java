package teamzesa.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;

public class DisplayEntity extends ComponentExchanger implements Listener {

    @EventHandler
    public void onEntity(@NotNull BlockPlaceEvent e) {
        Location position = e.getBlock().getLocation().add(0,10,0);
        Player player = e.getPlayer();
        HoverEvent<Component> hoverEvent = HoverEvent.showText(
                componentExchanger("Test"));
    }
}
