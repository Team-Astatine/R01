package teamzesa.event.register;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jetbrains.annotations.NotNull;
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;
import teamzesa.event.*;
import teamzesa.event.AntiExploit.*;

public class EventRegisterSection implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void redStoneEvent(@NotNull BlockRedstoneEvent event) {
//        methodImplement
//        new AntiRedStoneCrash(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void blockFallingEvent(@NotNull BlockPhysicsEvent event) {
//        methodImplement
//        new AntiGravityCrash(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void blockFallingEvent(@NotNull ChunkLoadEvent event) {
//        methodImplement
//        new AntiExploitFromChunkEvent(event);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void joinEvent(@NotNull PlayerJoinEvent event) {
        new DefaultJoinPlayerStatusEvent(event);
        new FirstJoinKitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void quitEvent(@NotNull PlayerQuitEvent event) {
        new QuitMsgEvent(event);
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
//        methodImplement
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
//        methodImplement
//        new EnhanceStuffEvent(event);
        new AntiLeverAutoClicker(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityPortalTouchingEvent(@NotNull EntityPortalEvent event) {
        new AntiPortalChunkRenderingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void pistonExtendBlockEvent(@NotNull BlockPistonExtendEvent event) {
        new AntiPistonPushGravityBlockEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void commandSendEvent(@NotNull PlayerCommandPreprocessEvent event) {
        new CommandSendEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerSwapStuffEvent(@NotNull PlayerSwapHandItemsEvent event) {
//        methodImplement
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
