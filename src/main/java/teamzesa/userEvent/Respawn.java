package teamzesa.userEvent;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;


public class Respawn implements Listener {
    private PlayerRespawnEvent event;

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        this.event = e;
        deathRandomTeleport();
    }

    public void deathRandomTeleport() {
        Player player = this.event.getPlayer();

        if (player.getBedSpawnLocation() != null) {
            ComponentExchanger.playerAnnouncer(player, "침대로 텔레포트 됩니다.", ColorList.YELLOW);
            return;
        }

        int x = RanNumGenerator.numGenerator();
        int z = RanNumGenerator.numGenerator();
        int y = RanNumGenerator.groundChecker(player.getWorld());

        this.event.setRespawnLocation(new Location(player.getWorld(),x,y,z));
        ComponentExchanger.playerAnnouncer(player,"침대가 없어 랜덤 텔레포트 되었습니다.", ColorList.ORANGE);
    }
}
