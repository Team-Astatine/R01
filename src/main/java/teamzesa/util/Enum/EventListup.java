package teamzesa.util.Enum;

import org.bukkit.event.Listener;
import teamzesa.event.EntityDamageTicking;
import teamzesa.event.Explosive;
import teamzesa.event.HandSwing;
import teamzesa.event.Death;
import teamzesa.event.Respawn;
import teamzesa.event.JoinAndQuit;
import teamzesa.event.Anvil;
import teamzesa.event.RaidAnnouncer;
import teamzesa.event.RecipeController;


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
    ENTITY_DAMAGE_TICKING(new EntityDamageTicking()),

    ANVIL(new Anvil());

    private final Listener eventListener;

    EventListup(Listener eventListener) {
        this.eventListener = eventListener;
    }

    public Listener newInstance() {
        return eventListener;
    }
}
