package teamzesa.event.Enhance.LongRange;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceCrossBowHitEvent extends EnhanceUtil implements EventRegister {
    private Location bowHitLocation;
    private final ProjectileHitEvent event;

    public EnhanceCrossBowHitEvent(ProjectileHitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        if (!(this.event.getEntity() instanceof Arrow arrow))
            return;

        if (arrow.isShotFromCrossbow())
            return;

        if (!(this.event.getEntity().getShooter() instanceof Player player))
            return;

        this.bowHitLocation = arrow.getLocation();
        ItemStack mainHandBow = checkModelData(player.getInventory().getItemInMainHand());

        switch (getItemCustomModelData(mainHandBow)) {
            case 1,2,3 -> executeEnhanceState(Sound.ENTITY_GHAST_DEATH, false);
            case 4,5,6 -> executeEnhanceState(Sound.ENTITY_ENDER_DRAGON_HURT, false);
            case 7,8,9 -> executeEnhanceState(Sound.ENTITY_ENDER_DRAGON_SHOOT, false);
            case 10 ->    executeEnhanceState(Sound.BLOCK_CONDUIT_ACTIVATE, true);
            default -> {
                return;
            }
        }
    }

    private void executeEnhanceState(Sound sound, boolean isFire) {
        Runnable tridentHitTask = () ->
            this.bowHitLocation.getWorld().playSound(this.bowHitLocation, sound, 5F, 5F);

        tridentHitTask.run();
    }
}
