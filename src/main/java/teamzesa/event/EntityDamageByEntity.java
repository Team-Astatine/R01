package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class EntityDamageByEntity implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public synchronized void entityHit(@NotNull EntityDamageByEntityEvent e) {

        Entity damagerEntity = e.getDamager();
        if (!damagerEntity.getType().equals(EntityType.PLAYER))
            return;

        Entity targetEntity = e.getEntity();
        if (!targetEntity.getType().equals(EntityType.PLAYER))
            return;

        Player damager = (Player) damagerEntity;
        Player target = (Player) targetEntity;

        int hurtTick = 20;
        boolean stuffCheck = handStuffChecker(
                damager.getInventory().getItemInMainHand().getType(),
                damager.getInventory().getItemInOffHand().getType());

        if (!stuffCheck) //One Hand Sword
            hurtTick = 10;

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

//        default 20
//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
        target.setMaximumNoDamageTicks(hurtTick);
    }

    private @NotNull Boolean handStuffChecker(@NotNull Material mainStuff , @NotNull Material offStuff) {
        Predicate<Material> netheriteToolChecker = tool ->
                tool.equals(Material.NETHERITE_SWORD) || tool.equals(Material.NETHERITE_AXE);

        return netheriteToolChecker.test(mainStuff) && netheriteToolChecker.test(offStuff);
    }
    /*private @NotNull Boolean handStuffChecker(@NotNull ItemStack mainStuff, @NotNull ItemStack offStuff) {
        Predicate<Material> isNetheriteSword = material -> material.equals(Material.NETHERITE_SWORD);
        Predicate<Material> isNetheriteAxe = material -> material.equals(Material.NETHERITE_AXE);

        Predicate<Material> isNetheriteTool = isNetheriteSword.or(isNetheriteAxe);

        return isNetheriteTool.test(mainStuff.getType()) && isNetheriteTool.test(offStuff.getType());
    }*/
}