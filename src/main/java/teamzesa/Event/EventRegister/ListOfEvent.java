package teamzesa.Event.EventRegister;

import io.papermc.paper.event.player.AsyncChatEvent;
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
import teamzesa.Event.Enhance.PlayerInteraction.Armour.EnhanceArmourResistanceArmour;
import teamzesa.Event.Enhance.PlayerInteraction.UserInterface.EnhanceUIClickEvent;
import teamzesa.Event.Enhance.PlayerInteraction.UserInterface.EnhanceUICloseEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.GodMode.GodModeTridentHitEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.GodMode.GodModeTridentShotEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.Hit.EnhanceBowHitEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.Hit.EnhanceCrossBowHitEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.Hit.EnhanceTridentHitEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.Shot.EnhanceBowShotEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.Shot.EnhanceCrossBowShotEvent;
import teamzesa.Event.Enhance.PlayerInteraction.LongRange.Shot.EnhanceTridentShotEvent;
import teamzesa.Event.Enhance.PlayerInteraction.ShortRange.EnhanceShortRangeWeaponHurtEvent;
import teamzesa.Event.Enhance.PlayerInteraction.UpdateItemLore.*;
import teamzesa.Event.EntityDamageByEntityEvent.EntityAttackSpeedClear;
import teamzesa.Event.EntityDamageByEntityEvent.EntityAttackSpeedHandler;
import teamzesa.Event.EntityDeathEvent.BossDeathListener;
import teamzesa.Event.EntityExplodeEvent.ExplosiveEvent;
import teamzesa.Event.PlayerDeathEvent.DropDeadsHead;
import teamzesa.Event.PlayerSwapHandItemsEvent.MenuOpener;
import teamzesa.Event.Restricted.RestrictedExplosiveDamageManager;
import teamzesa.Event.PlayerArmSwingEvent.HandSwingEvent;
import teamzesa.Event.PlayerDeathEvent.LifeSteelEvent;
import teamzesa.Event.PlayerDeathEvent.UpdateKillStatusForKiller;
import teamzesa.Event.PlayerJoinEvent.ImportPlayerStatusEvent;
import teamzesa.Event.PlayerJoinEvent.PlayerFlyEnableEvent;
import teamzesa.Event.PlayerJoinEvent.PlayerInfoHandler;
import teamzesa.Event.PlayerQuitEvent.QuitMsgEvent;
import teamzesa.Event.PlayerRespawnEvent.RespawnEvent;
import teamzesa.Event.PlayerRespawnEvent.RespawnRandomTeleportEvent;
import teamzesa.Event.Restricted.AntiExploit.AntiPistonPushGravityBlockEvent;
import teamzesa.Event.Restricted.AntiExploit.AntiPortalChunkRenderingEvent;
import teamzesa.Event.Restricted.AntiExploit.FloodChat.RestrictedChatFlood;
import teamzesa.Event.Restricted.AntiExploit.LeverInteraction.LeverInteractionHandler;
import teamzesa.Event.Restricted.*;

/**
 * {@linkplain org.bukkit.event.Listener}의 구현체를 관리합니다.
 * @performance RunTime시 해당 클래스는 하나의 Instance만 생성됩니다.
 *
 * 개발 진행 시 필요에 따라 각 태그를 사용하여 기록합니다.
 * todo
 * fixme
 * refactoring
 * Implement
 * methodImplement
 * funImplement
 * debug
 *
 * {@link EventPriority}로 모든 이벤트의 우선순위를 정합니다.
 * Event 처리의 우선순위는 역순이며, 자세한건 {@linkplain EventHandler}, {@link EventPriority}를 참고해주세요.
 * {@link EventHandler#ignoreCancelled()}는 이벤트 취소 시 더 이상 Runtime에서 호출하지 않습니다. {@link EventHandler} 참고해주세요.
 *
 * 각 함수는 {@linkplain Listener}을 상속받고 있는 EventClass 와 1 대 1로 명칭이 매칭됩니다.
 * 이벤트를 추가하고 싶다면, Event를 선택하여 해당 함수에 객체를 생성 후, {@link EventRegister}를 상속받아 구현합니다.
 * 사용하지 않지만, 개발된 함수는 일단 적어둔 후 todo 태그로 관리힙니다.
 */
public class ListOfEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockRedstoneEvent(BlockRedstoneEvent event) {
//        methodImplement
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockPhysicsEvent(BlockPhysicsEvent event) {
//        methodImplement
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ChunkLoadEvent(ChunkLoadEvent event) {
//        methodImplement
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        new PlayerInfoHandler(event);
        new PlayerFlyEnableEvent(event);
        new ImportPlayerStatusEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        new QuitMsgEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void RaidTriggerEvent(RaidTriggerEvent event) {
//        todo 반야생변경에 따른 콘텐츠 수정
//        new RaidAnnouncerEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        new LifeSteelEvent(event);
        new UpdateKillStatusForKiller(event);
        new DropDeadsHead(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDeathEvent(EntityDeathEvent event) {
        new BossDeathListener(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EnchantItemEvent(EnchantItemEvent event) {
        new UpdateEnhanceItemLoreFromEnchantment(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PrepareAnvilEvent(PrepareAnvilEvent event) {
        new UpdateEnhanceItemPrepareAnvil(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PrepareGrindstoneEvent(PrepareGrindstoneEvent event) {
        new UpdateEnhanceItemPrepareGrindstone(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ProjectileHitEvent(ProjectileHitEvent event) {
        new EnhanceBowHitEvent(event);
        new EnhanceCrossBowHitEvent(event);
        new EnhanceTridentHitEvent(event);
        new GodModeTridentHitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ProjectileLaunchEvent(ProjectileLaunchEvent event) {
        new EnhanceBowShotEvent(event);
        new EnhanceCrossBowShotEvent(event);
        new EnhanceTridentShotEvent(event);
        new GodModeTridentShotEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        new RespawnEvent(event);
        new PlayerFlyEnableEvent(event);
        new RespawnRandomTeleportEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryClickEvent(InventoryClickEvent event) {
        new RestrictedItemInputDispenserHandler(event);
        new RestrictedStackingTotemInteraction(event);
        new RestrictedShulkerChest(event);

        new EnhanceUIClickEvent(event);
        new UpdateEnhanceResultItemLoreFromAnvil(event);
        new UpdateEnhanceResultItemLoreFromGrindStone(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryCloseEvent(InventoryCloseEvent event) {
        new EnhanceUICloseEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryMoveItemEvent(InventoryMoveItemEvent event) {
        new RestrictedInvToInvMoveItemHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityExplodeEvent(EntityExplodeEvent event) {
        new ExplosiveEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerArmSwingEvent(PlayerArmSwingEvent event) {
        new HandSwingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDamageEvent(EntityDamageEvent event) {
//        Event Cancelled 하면 해당 Event 자체가 캔슬됌.
        new EnhanceArmourResistanceArmour(event);
        new RestrictedExplosiveDamageManager(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        new EntityAttackSpeedClear(event);
        new LeverInteractionHandler(event);
        new RestrictedEntityPlayerInteractHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityPortalEvent(EntityPortalEvent event) {
        new AntiPortalChunkRenderingEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockPistonExtendEvent(BlockPistonExtendEvent event) {
        new AntiPistonPushGravityBlockEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void TabCompleteEvent(TabCompleteEvent event) {
//        todo permission 세팅으로 해결함.
//        new RestrictedCommandTabCompleteEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        new RestrictedCommandHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void AsyncChatEvent(AsyncChatEvent event) {
        new RestrictedChatFlood(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        new MenuOpener(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        new PlayerFlyEnableEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        new EntityAttackSpeedHandler(event);
        new EnhanceShortRangeWeaponHurtEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void CraftItemEvent(CraftItemEvent event) {
//        todo permission 세팅으로 해결함.
//        new RestrictedItemCraftHandler(event);
    }
}
