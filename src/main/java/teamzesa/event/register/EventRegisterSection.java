package teamzesa.event.register;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.jetbrains.annotations.NotNull;
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;
import teamzesa.event.*;

public class EventRegisterSection implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void joinEvent(@NotNull PlayerJoinEvent event) {
        new JoinEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void quitEvent(@NotNull PlayerQuitEvent event) {
        new QuitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void raidEvent(@NotNull RaidTriggerEvent event) {
        new RaidAnnouncerEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void deathEvent(@NotNull PlayerDeathEvent event) {
        new DeathEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void throwWeaponHitEvent(ProjectileHitEvent event) {
        new GodModeTridentHitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void weaponThrowEvent(ProjectileLaunchEvent event) {
        new GodModeTridentThrowEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void respawnEvent(@NotNull PlayerRespawnEvent event) {
        new RespawnEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void invClickEvent(@NotNull InventoryClickEvent event) {

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void explosiveEvent(@NotNull EntityExplodeEvent event) {
        new ExplosiveEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void armSwingEvent(@NotNull PlayerArmSwingEvent event) {
        new HandSwingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void anvilResult(@NotNull AnvilUpdateResultEvent event) {
        new AnvilResultEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerInteractEvent(@NotNull PlayerInteractEvent event) {
//        new EnhanceStuffEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityPortalTouchingEvent(@NotNull EntityPortalEvent event) {
        new AntiPortalChunkRenderingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void pistonExtendBlockEvent(@NotNull BlockPistonExtendEvent event) {
        new PistonPushEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerSwapStuffEvent(@NotNull PlayerSwapHandItemsEvent event) {

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityToEntityDamageEvent(@NotNull EntityDamageByEntityEvent event) {
        new EntityDmgByEntityEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void craftStuffEvent(CraftItemEvent event) {
        new CraftEvent(event);
    }
}
