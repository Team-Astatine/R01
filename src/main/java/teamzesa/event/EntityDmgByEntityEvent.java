package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import teamzesa.event.register.EventRegister;

import java.util.function.Predicate;

public class EntityDmgByEntityEvent implements EventRegister {
    private Entity damagerEntity;
    private Entity targetEntity;
    private final EntityDamageByEntityEvent event;

    public EntityDmgByEntityEvent(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.damagerEntity = this.event.getDamager();
        this.targetEntity = this.event.getEntity();
    }

    @Override
    public void execute() {
        if (!(this.damagerEntity instanceof Player damager))
            return;

        if (!(this.targetEntity instanceof Player target))
            return;

        int hurtTick = 20; //default 20
        boolean stuffCheck = handStuffChecker(
                damager.getInventory().getItemInMainHand().getType(),
                damager.getInventory().getItemInOffHand().getType());

        if (!stuffCheck) //One Hand Sword
            hurtTick = 10;

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

//        ((LivingEntity) e.getEntity()).setMaximumNoDamageTicks(1);
        target.setMaximumNoDamageTicks(hurtTick);
    }

    private @NotNull Boolean handStuffChecker(@NotNull Material mainStuff, @NotNull Material offStuff) {
        Predicate<Material> netheriteToolChecker = tool ->
                tool.equals(Material.NETHERITE_SWORD) || tool.equals(Material.NETHERITE_AXE);

        return netheriteToolChecker.test(mainStuff) && netheriteToolChecker.test(offStuff);
    }
}