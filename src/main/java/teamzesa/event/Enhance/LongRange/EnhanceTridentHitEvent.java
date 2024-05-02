package teamzesa.event.Enhance.LongRange;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceTridentHitEvent extends EnhanceUtil implements EventRegister {
    private Player player;
    private Trident trident;
    private ItemStack playerHandTrident;
    private Location tridentHitLocation;
    private final ProjectileHitEvent event;

    public EnhanceTridentHitEvent(ProjectileHitEvent event) {
        this.event = event;

        if (!(this.event.getEntity() instanceof Trident trident))
            return;

        if (!(this.event.getEntity().getShooter() instanceof Player player))
            return;

        this.player = player;
        this.trident = trident;
        this.playerHandTrident = this.player.getInventory().getItemInMainHand();

        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        this.tridentHitLocation = this.trident.getLocation();
        switch (getItemCustomModelData(this.playerHandTrident)) {
            case 1,2,3 -> executeEnhanceState(Sound.ENTITY_GHAST_DEATH, 0, false);
            case 4,5,6 -> executeEnhanceState(Sound.ENTITY_ENDER_DRAGON_HURT, 1F, false);
            case 7,8,9 -> executeEnhanceState(Sound.ENTITY_ENDER_DRAGON_AMBIENT, 3F, false);
            case 10 ->    executeEnhanceState(Sound.BLOCK_CONDUIT_ACTIVATE, 6F, true);
            default -> {
                return;
            }
        }
    }

    private void executeEnhanceState(Sound sound, float power, boolean isFire) {
        Runnable tridentHitTask = () -> {
            this.tridentHitLocation.getWorld().playSound(this.tridentHitLocation, sound, 5F, 5F);
            this.tridentHitLocation.getWorld().createExplosion(this.tridentHitLocation, power, isFire);
        };

        tridentHitTask.run();
    }
}
