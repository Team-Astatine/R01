package teamzesa.combat;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EntityDamageTicking implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityHit(@NotNull EntityDamageByEntityEvent e) {
        if (!e.getDamager().getType().equals(EntityType.PLAYER))
            return;

        if (!e.getEntityType().equals(EntityType.PLAYER))
            return;

        Player heater = (Player) e.getDamager();
        Player target = (Player) e.getEntity();

//        default 20
//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
        int hurtTick = 20;
        boolean stuffCheck = handStuffChecker(
                heater.getInventory().getItemInMainHand(),
                heater.getInventory().getItemInOffHand());

        if (!stuffCheck) //One Hand Sword
            hurtTick = 10;

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

        target.setMaximumNoDamageTicks(hurtTick);
    }

    private @NotNull Boolean handStuffChecker(@NotNull ItemStack mainStuff , @NotNull ItemStack offStuff) {
        Optional<Material> mainStuffType = Optional.of(mainStuff.getType());
        Optional<Material> offStuffType = Optional.of(offStuff.getType());

        boolean mainHand = mainStuffType.filter(stuff ->
            stuff.equals(Material.NETHERITE_SWORD) || stuff.equals(Material.NETHERITE_AXE)
        ).isPresent();

        boolean offHand = offStuffType.filter(stuff ->
                stuff.equals(Material.NETHERITE_SWORD) || stuff.equals(Material.NETHERITE_AXE)
        ).isPresent();

        return mainHand && offHand;
    }
}
