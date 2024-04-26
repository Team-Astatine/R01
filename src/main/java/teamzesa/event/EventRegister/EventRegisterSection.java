package teamzesa.event.EventRegister;

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
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;
import teamzesa.event.*;
import teamzesa.event.AntiExploit.*;
import teamzesa.event.Enhance.EnhanceInventoryClickEvent;
import teamzesa.event.Enhance.EnhanceInventoryCloseEvent;
import teamzesa.event.Enhance.EnhanceItemDmgEvent;
//todo
//fixme
//refactoring
//Implement
//methodImplement
//funImplement
//debug

public class EventRegisterSection implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public static void BlockRedstoneEvent(BlockRedstoneEvent event) {
//        methodImplement
//        new AntiRedStoneCrash(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void BlockPhysicsEvent(BlockPhysicsEvent event) {
//        methodImplement
//        new AntiGravityCrash(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void ChunkLoadEvent(ChunkLoadEvent event) {
//        methodImplement
//        new AntiExploitFromChunkEvent(event);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerJoinEvent(PlayerJoinEvent event) {
        new PlayerInfoHandler(event);
        new PlayerFlyEnableEvent(event);
        new ImportPlayerStatusEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerQuitEvent(PlayerQuitEvent event) {
        new QuitMsgEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void RaidTriggerEvent(RaidTriggerEvent event) {
        new RaidAnnouncerEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerDeathEvent(PlayerDeathEvent event) {
        new LifeSteelEvent(event);
        new PlayerFlyEnableEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void ProjectileHitEvent(ProjectileHitEvent event) {
        new GodModeTridentHitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void ProjectileLaunchEvent(ProjectileLaunchEvent event) {
        new GodModeTridentThrowEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerRespawnEvent(PlayerRespawnEvent event) {
        new RespawnEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void InventoryClickEvent(InventoryClickEvent event) {
        new RestrictedShulkerChest(event);
        new EnhanceInventoryClickEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void InventoryCloseEvent(InventoryCloseEvent event) {
        new EnhanceInventoryCloseEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EntityExplodeEvent(EntityExplodeEvent event) {
        new ExplosiveEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerArmSwingEvent(PlayerArmSwingEvent event) {
        new HandSwingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void AnvilUpdateResultEvent(AnvilUpdateResultEvent event) {
        new AnvilLimitHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EntityDamageEvent(EntityDamageEvent event) {
        new EnhanceItemDmgEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        new AntiLeverAutoClicker(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EntityPortalEvent(EntityPortalEvent event) {
        new AntiPortalChunkRenderingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void BlockPistonExtendEvent(BlockPistonExtendEvent event) {
        new AntiPistonPushGravityBlockEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        new BanCommandHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
//        methodImplement

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        new PlayerFlyEnableEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        new EntityAttackSpeedHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public static void CraftItemEvent(CraftItemEvent event) {
        new BanItemHandler(event);
    }
}
