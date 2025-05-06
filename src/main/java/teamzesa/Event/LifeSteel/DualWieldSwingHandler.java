package teamzesa.Event.LifeSteel;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationType;
import teamzesa.R01;
import teamzesa.Event.EventRegister;


public class DualWieldSwingHandler implements EventRegister {
    private Player player;
    private final PlayerArmSwingEvent event;

    public DualWieldSwingHandler(PlayerArmSwingEvent event) {
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
        if (ObjectUtils.notEqual(this.player.getInventory().getItemInMainHand(), Material.AIR))
            return;

        if (ObjectUtils.notEqual(this.player.getInventory().getItemInOffHand().getType(), Material.AIR))
            return;

        if (ObjectUtils.notEqual(this.event.getAnimationType(), PlayerAnimationType.OFF_ARM_SWING))
            return;

        Bukkit.getScheduler().runTaskLater(
                R01.getPlugin(R01.class),
                () -> this.event.getPlayer().swingOffHand(),
                7L
        );
    }
}
