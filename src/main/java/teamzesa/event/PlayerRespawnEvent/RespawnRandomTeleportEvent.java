package teamzesa.event.PlayerRespawnEvent;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.WorldName;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.RanNumGenerator;


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
        if (this.event.isAnchorSpawn())
            return;

        if (ObjectUtils.notEqual(this.player.getPotentialBedLocation(), null))
            return;

        World world = Bukkit.getWorld(WorldName.world.getExchangeEnglish());
        int[] position = new RanNumGenerator().groundChecker(world);

        int x = position[0];
        int y = position[1];
        int z = position[2];

        this.player.teleportAsync(new Location(world, x, y, z));
    }
}
