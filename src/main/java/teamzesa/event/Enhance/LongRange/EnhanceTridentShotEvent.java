package teamzesa.event.Enhance.LongRange;

import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceTridentShotEvent extends EnhanceUtil implements EventRegister {
    private Trident trident;
    private final ProjectileLaunchEvent event;

    public EnhanceTridentShotEvent(ProjectileLaunchEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        if (!(this.event.getEntity() instanceof Trident trident))
            return;

        if (!(this.event.getEntity().getShooter() instanceof Player player))
            return;

        this.trident = trident;
        ItemStack mainHandTrident = player.getInventory().getItemInMainHand();

        Vector vector = this.event.getEntity().getVelocity();
        switch (getItemCustomModelData(mainHandTrident)) {
            case 1,2,3 -> executeEnhanceState(1,1, 1, Sound.ENTITY_GHAST_DEATH, vector);
            case 4,5,6 -> executeEnhanceState(1,2, 3, Sound.ENTITY_ENDER_DRAGON_HURT, vector);
            case 7,8,9 -> executeEnhanceState(2,3, 6, Sound.ENTITY_WITHER_SPAWN, vector);
            case 10 -> executeEnhanceState(3,5, 10, Sound.BLOCK_CONDUIT_ACTIVATE, vector);
            default -> {
                return;
            }
        }
    }

    private void executeEnhanceState(int shootingSpeed, int loyaltyLevel, int pierceLevel, Sound sound, Vector vector) {
        Runnable tridentThrowingTask = () -> {
            this.trident.setGlowing(true);
            this.trident.setPierceLevel(pierceLevel);
            this.trident.setLoyaltyLevel(loyaltyLevel);
            this.event.getEntity().setVelocity(vector.multiply(shootingSpeed));
            this.trident.getWorld().playSound(trident.getLocation(), sound, 5F, 5F);
        };

        tridentThrowingTask.run();
    }
}
