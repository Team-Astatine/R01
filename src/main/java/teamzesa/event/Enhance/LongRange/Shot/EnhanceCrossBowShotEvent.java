package teamzesa.event.Enhance.LongRange.Shot;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.util.Vector;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class EnhanceCrossBowShotEvent extends EnhanceUtil implements EventRegister {
    private Arrow arrow;
    private final ProjectileLaunchEvent event;
    public EnhanceCrossBowShotEvent(ProjectileLaunchEvent event) {
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

        if (!arrow.isShotFromCrossbow())
            return;

        if (!(this.event.getEntity().getShooter() instanceof Player shooter))
            return;

        this.arrow = arrow;
        ItemStack mainHandCrossBow = shooter.getInventory().getItemInMainHand();

        if (mainHandCrossBow.getType() != Material.CROSSBOW)
            mainHandCrossBow = shooter.getInventory().getItemInOffHand();

        if (!mainHandCrossBow.hasItemMeta())
            return;

        if (!mainHandCrossBow.getItemMeta().hasCustomModelData())
            return;

        Vector vector = this.event.getEntity().getVelocity();
        switch (getItemCustomModelData(mainHandCrossBow)) {
            case 1,2,3 -> executeEnhanceState(1, 1, Sound.ENTITY_GHAST_DEATH, vector);
            case 4,5,6 -> executeEnhanceState(1, 3, Sound.ENTITY_ENDER_DRAGON_HURT, vector);
            case 7,8,9 -> executeEnhanceState(2, 6, Sound.ENTITY_ENDER_DRAGON_SHOOT, vector);
            case 10 ->    {
                executeEnhanceState(3, 10, Sound.BLOCK_CONDUIT_ACTIVATE, vector);
                CrossbowMeta mainHand = (CrossbowMeta) mainHandCrossBow.getItemMeta();
                mainHand.addChargedProjectile(new ItemStack(Material.ARROW));
                mainHandCrossBow.setItemMeta(mainHand);
            }
            default -> {
                return;
            }
        }
    }

    private void executeEnhanceState(int shootingSpeed, int pierceLevel, Sound sound, Vector vector) {
        Runnable tridentThrowingTask = () -> {
            this.arrow.setPierceLevel(pierceLevel);
            this.event.getEntity().setVelocity(vector.multiply(shootingSpeed));
            this.arrow.getWorld().playSound(arrow.getLocation(), sound, 5F, 5F);


            this.arrow.setCritical(false);
        };

        tridentThrowingTask.run();
    }
}
