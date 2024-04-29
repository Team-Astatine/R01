package teamzesa.event.Enhance.LongRange;

import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceTridentShotEvent extends EnhanceUtil implements EventRegister {
    private Player player;
    private Trident trident;
    private ItemStack playerHandTrident;
    private final ProjectileLaunchEvent event;

    public EnhanceTridentShotEvent(ProjectileLaunchEvent event) {
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
        Vector vector = this.event.getEntity().getVelocity();
        switch (getItemCustomModelData(this.playerHandTrident)) {
            case 0 -> this.event.getEntity().setVelocity(vector.multiply(1));
            case 1,2,3 -> executeEnhanceState(1,1, 1, Sound.ENTITY_GHAST_DEATH, vector);
            case 4,5,6 -> executeEnhanceState(1,2, 3, Sound.ENTITY_ENDER_DRAGON_HURT, vector);
            case 7,8,9 -> executeEnhanceState(2,3, 6, Sound.ENTITY_ENDER_DRAGON_AMBIENT, vector);
            case 10 -> executeEnhanceState(3,5, 10, Sound.BLOCK_CONDUIT_ACTIVATE, vector);

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
