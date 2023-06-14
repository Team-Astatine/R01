package teamzesa.attackSpeed;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerAttackSpeed implements Listener {
//    private static PlayerAttackSpeed instance;

//    private PlayerAttackSpeed() {}

//    public static PlayerAttackSpeed getInstance() {
//        if (instance == null)
//            return new PlayerAttackSpeed();
//        return instance;
//    }

    @EventHandler
    public void attackSpeedJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

}
