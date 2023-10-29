package teamzesa.resgisterEvent;

import org.bukkit.event.Listener;
import teamzesa.combat.EntityDamageTicking;
import teamzesa.combat.Explosive;
import teamzesa.combat.HandSwing;
import teamzesa.userEvent.Death;
import teamzesa.userEvent.Respawn;
import teamzesa.userEvent.JoinAndQuit;
import teamzesa.worldSet.RaidAnnouncer;
import teamzesa.worldSet.RecipeController;


public enum EventListup {
//    ANVIL(new Anvil()),
//    DISPLAY_ENTITY(new DisplayEntity()),

    DEATH(new Death()),
    RESPAWN(new Respawn()),
    EXPLOSIVE(new Explosive()),
    HAND_SWING(new HandSwing()),
    JOIN_AND_QUIT(new JoinAndQuit()),
    RAID_ANNOUNCER(new RaidAnnouncer()),
    RECIPE_CONTROLLER(new RecipeController()),
    ENTITY_DAMAGE_TICKING(new EntityDamageTicking());

    private final Listener eventListener;

    EventListup(Listener eventListener) {
        this.eventListener = eventListener;
    }

    public Listener newInstance() {
        return eventListener;
    }
}
