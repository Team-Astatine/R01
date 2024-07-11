package teamzesa.event.Enhance.LongRange.Hit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.DataBase.userHandler.UserController;

public class EnhanceTridentHitEvent extends EnhanceUtil implements EventRegister {
    private Location tridentHitLocation;
    private final ProjectileHitEvent event;

    public EnhanceTridentHitEvent(ProjectileHitEvent event) {
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

        if (!(this.event.getEntity().getShooter() instanceof Player shooter))
            return;

        if (new UserController().readUser(shooter.getUniqueId()).isGodMode())
            return;

        this.tridentHitLocation = trident.getLocation();
        ItemStack mainHandTrident = shooter.getInventory().getItemInMainHand();

        if (mainHandTrident.getType() != Material.TRIDENT)
            mainHandTrident = shooter.getInventory().getItemInOffHand();

        if (!mainHandTrident.hasItemMeta())
            return;

        if (!mainHandTrident.getItemMeta().hasCustomModelData())
            return;

        switch (getItemCustomModelData(mainHandTrident)) {
            case 1,2,3 -> executeEnhanceState(Sound.ENTITY_GHAST_DEATH, 0, false);
            case 4,5,6 -> executeEnhanceState(Sound.ENTITY_ENDER_DRAGON_HURT, 1F, false);
            case 7,8,9 -> executeEnhanceState(Sound.ENTITY_ENDER_DRAGON_AMBIENT, 3F, false);
            case 10 ->    executeEnhanceState(Sound.ENTITY_WITHER_SPAWN, 6F, true);
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
