package teamzesa.event.register;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jetbrains.annotations.NotNull;
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;
import teamzesa.event.*;
import teamzesa.event.AntiExploit.*;
import teamzesa.event.Enhance.EnhanceInventoryClickEvent;
//todo
//fixme
//refactoring
//Implement
//methodImplement
//funImplement
//debug

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
        new PlayerInfoHandler(event);
        new PlayerFlyEnableEvent(event);
        new ImportPlayerStatusEvent(event);
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
        new LifeSteelEvent(event);
        new PlayerFlyEnableEvent(event);
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
        new RestrictedShulkerChest(event);
        new EnhanceInventoryClickEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void invMoveItemEvent(@NotNull InventoryCloseEvent event) {
//        methodImplement
//        enhanceInv close event 추가할것
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
        new AnvilLimitHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerInteractEvent(@NotNull PlayerInteractEvent event) {
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
        new BanCommandHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerSwapStuffEvent(@NotNull PlayerSwapHandItemsEvent event) {
//        methodImplement

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityPortalTouchingEvent(@NotNull PlayerChangedWorldEvent event) {
        new PlayerFlyEnableEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void entityToEntityDamageEvent(@NotNull EntityDamageByEntityEvent event) {
        new EntityAttackSpeedHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void craftStuffEvent(CraftItemEvent event) {
        new BanItemHandler(event);
    }
}
