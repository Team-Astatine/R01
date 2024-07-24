package teamzesa.event.PlayerRespawnEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.WorldName;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.RanNumGenerator;

import java.util.Arrays;

public class RespawnRandomTeleportEvent extends StringComponentExchanger implements EventRegister {
    private Player player;

    private final PlayerRespawnEvent event;
    public RespawnRandomTeleportEvent(PlayerRespawnEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
    }

    @Override
    public void execute() {
        if (this.player.getPotentialBedLocation() != null)
            return;

        World world = Bukkit.getWorld(WorldName.world.getExchangeEnglish());
        int[] position = RanNumGenerator.groundChecker(world);

        int x = position[0];
        int y = position[1];
        int z = position[2];

//        System.out.println(Arrays.toString(position));
        this.player.teleportAsync(new Location(world,x,y,z));
    }
}
