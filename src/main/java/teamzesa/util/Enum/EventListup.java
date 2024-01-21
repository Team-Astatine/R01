package teamzesa.util.Enum;

import org.bukkit.event.Listener;
import teamzesa.event.*;


public enum EventListup {
//    DISPLAY_ENTITY(new DisplayEntity()),

//    MENU(new Menu()),
    ANVIL(new Anvil()),
    DEATH(new Death()),
    RESPAWN(new Respawn()),
    GOD_EVENT(new GodEvent()),
    EXPLOSIVE(new Explosive()),
    HAND_SWING(new HandSwing()),
    JOIN_AND_QUIT(new JoinAndQuit()),
    RAID_ANNOUNCER(new RaidAnnouncer()),
    RECIPE_CONTROLLER(new RecipeController()),
    ENTITY_DAMAGE_TICKING(new EntityDamageByEntity());

    private final Listener eventListener;

    EventListup(Listener eventListener) {
        this.eventListener = eventListener;
    }

    public Listener newInstance() {
        return eventListener;
    }
}
