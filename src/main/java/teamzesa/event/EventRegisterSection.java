package teamzesa.event;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import teamzesa.event.Enhance.PlayerInteraction.EnhanceInventoryClickEvent;
import teamzesa.event.Enhance.PlayerInteraction.EnhanceInventoryCloseEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.GodMode.GodModeTridentHitEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.GodMode.GodModeTridentShotEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.Hit.EnhanceBowHitEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.Hit.EnhanceCrossBowHitEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.Hit.EnhanceTridentHitEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.Shot.EnhanceBowShotEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.Shot.EnhanceCrossBowShotEvent;
import teamzesa.event.Enhance.PlayerInteraction.LongRange.Shot.EnhanceTridentShotEvent;
import teamzesa.event.Enhance.PlayerInteraction.ShortRange.EnhanceShortRangeWeaponHurtEvent;
import teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceItemLoreFromEnchantment;
import teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceItemPrepareAnvil;
import teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceResultItemLoreFromAnvil;
import teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceResultItemLoreFromGrindStone;
import teamzesa.event.EntityDamageByEntityEvent.EntityAttackSpeedHandler;
import teamzesa.event.EntityExplodeEvent.ExplosiveEvent;
import teamzesa.event.PlayerArmSwingEvent.HandSwingEvent;
import teamzesa.event.PlayerDeathEvent.LifeSteelEvent;
import teamzesa.event.PlayerJoinEvent.ImportPlayerStatusEvent;
import teamzesa.event.PlayerJoinEvent.PlayerFlyEnableEvent;
import teamzesa.event.PlayerJoinEvent.PlayerInfoHandler;
import teamzesa.event.PlayerQuitEvent.QuitMsgEvent;
import teamzesa.event.PlayerRespawnEvent.RespawnEvent;
import teamzesa.event.PlayerRespawnEvent.RespawnRandomTeleportEvent;
import teamzesa.event.RaidTriggerEvent.RaidAnnouncerEvent;
import teamzesa.event.Restricted.AntiExploit.AntiPistonPushGravityBlockEvent;
import teamzesa.event.Restricted.AntiExploit.AntiPortalChunkRenderingEvent;
import teamzesa.event.Restricted.AntiExploit.LeverInteraction.AntiLeverAutoClicker;
import teamzesa.event.Restricted.*;
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
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EnchantItemEvent(EnchantItemEvent event) {
        new UpdateEnhanceItemLoreFromEnchantment(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EnchantItemEvent(PrepareAnvilEvent event) {
        new UpdateEnhanceItemPrepareAnvil();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void EnchantItemEvent(PrepareGrindstoneEvent event) {
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void ProjectileHitEvent(ProjectileHitEvent event) {
        new EnhanceBowHitEvent(event);
        new EnhanceCrossBowHitEvent(event);
        new EnhanceTridentHitEvent(event);
        new GodModeTridentHitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void ProjectileLaunchEvent(ProjectileLaunchEvent event) {
        new EnhanceBowShotEvent(event);
        new EnhanceCrossBowShotEvent(event);
        new EnhanceTridentShotEvent(event);
        new GodModeTridentShotEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerRespawnEvent(PlayerRespawnEvent event) {
        new RespawnEvent(event);
        new PlayerFlyEnableEvent(event);
        new RespawnRandomTeleportEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void InventoryClickEvent(InventoryClickEvent event) {
//        new RestrictedCrafter(event);
        new RestrictedItemInputDispenserHandler(event);
        new RestrictedShulkerChest(event);

        new EnhanceInventoryClickEvent(event);
        new UpdateEnhanceResultItemLoreFromAnvil(event);
        new UpdateEnhanceResultItemLoreFromGrindStone(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void InventoryCloseEvent(InventoryCloseEvent event) {
        new EnhanceInventoryCloseEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void InventoryMoveItemEvent(InventoryMoveItemEvent event) {
        new RestrictedInvToInvMoveItemHandler(event);
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
    public static void EntityDamageEvent(EntityDamageEvent event) {
//        Event Cancelled 하면 해당 Event 자체가 캔슬됌.
//        new EnhanceResistanceEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerInteractEvent(PlayerInteractEvent event) {
        new AntiLeverAutoClicker(event);
        new RestrictedEntityPInteractHandler(event);
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
    public static void TabCompleteEvent(TabCompleteEvent event) {
//        new RestrictedCommandTabCompleteEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        new RestrictedCommandHandler(event);
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
        new EnhanceShortRangeWeaponHurtEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public static void CraftItemEvent(CraftItemEvent event) {
//        new RestrictedItemCraftHandler(event);
    }
}
