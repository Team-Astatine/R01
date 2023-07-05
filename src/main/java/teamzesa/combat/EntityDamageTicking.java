package teamzesa.combat;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageTicking implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityHit(EntityDamageByEntityEvent e) {
        if (!e.getDamager().getType().equals(EntityType.PLAYER))
            return;

        if (!e.getEntityType().equals(EntityType.PLAYER))
            return;

        Player heater = (Player) e.getDamager();
        Player target = (Player) e.getEntity();

        //default 20
        int hurtTick = 20;
        boolean stuffCheck = handStuffChecker(
                heater.getInventory().getItemInMainHand() ,
                heater.getInventory().getItemInOffHand());

        if (!stuffCheck) //One Hand Sword
            hurtTick = 10;

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

        target.setMaximumNoDamageTicks(hurtTick);
//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
    }

    private Boolean handStuffChecker(ItemStack mainHand , ItemStack offHand) {
        if (mainHand.getType().equals(Material.NETHERITE_SWORD) && offHand.getType().equals(Material.NETHERITE_SWORD))
            return true;

        if (mainHand.getType().equals(Material.NETHERITE_AXE) && offHand.getType().equals(Material.NETHERITE_AXE))
            return true;

        if (mainHand.getType().equals(Material.NETHERITE_SWORD) && offHand.getType().equals(Material.NETHERITE_AXE))
            return true;

        if (mainHand.getType().equals(Material.NETHERITE_AXE) && offHand.getType().equals(Material.NETHERITE_SWORD))
            return true;

        return false;
    }
}
