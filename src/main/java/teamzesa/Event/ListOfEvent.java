package teamzesa.Event;

import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import io.papermc.paper.event.player.PlayerOpenSignEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import teamzesa.Event.Enhance.PlayerInteraction.Armour.EnhanceArmourResistanceArmour;
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
import teamzesa.Event.PlayerInteraction.Announce.JoinAndQuitMessage.JoinMessageHandler;
import teamzesa.Event.PlayerInteraction.Announce.Raid.RaidAnnouncerEvent;
import teamzesa.Event.PlayerInteraction.PlayerStatus.PlayerQuitEvent.PlayTimeUpdate;
import teamzesa.Event.RemoveHitDelay.EntityAttackSpeedHandler;
import teamzesa.Event.LifeSteel.BossDeathRewardHandler;
import teamzesa.Event.CustomizationExploded.ExplosiveEvent;
import teamzesa.Event.LifeSteel.DualWieldSwingHandler;
import teamzesa.Event.PlayerDeathEvent.DropDeadsHeadEvent;
import teamzesa.Event.LifeSteel.LifeSteelEvent;
import teamzesa.Event.PlayerInteraction.PlayerStatus.PlayerDeathEvent.IncreaseKillCountHandler;
import teamzesa.Event.PlayerInteraction.PlayerStatus.PlayerJoinEvent.ImportPlayerStatus;
import teamzesa.Event.PlayerInteraction.PlayerStatus.PlayerJoinEvent.PlayerFlyEnableEvent;
import teamzesa.Event.PlayerInteraction.PlayerStatus.PlayerJoinEvent.PlayerInfoHandler;
import teamzesa.Event.PlayerInteraction.Announce.JoinAndQuitMessage.QuitMessageHandler;
import teamzesa.Event.PlayerInteraction.PlayerStatus.PlayerRespawnEvent.RespawnMessageHandler;
import teamzesa.Event.RandomTeleport.RespawnRandomTeleportEvent;
import teamzesa.Event.UserInterface.Menu.MainMenuUI;
import teamzesa.Event.Restricted.AntiExploit.ChatFlood.RestrictedChatFlood;
import teamzesa.Event.Restricted.AntiExploit.ChunkRenderer.AntiPortalChunkRenderingEvent;
import teamzesa.Event.Restricted.AntiExploit.Gravity.AntiPistonPushGravityBlockEvent;
import teamzesa.Event.Restricted.AntiExploit.Interaction.InventoryInteraction.Dispenser.RestrictedDispenserInventoryMoveItemHandler;
import teamzesa.Event.Restricted.AntiExploit.Interaction.InventoryInteraction.Dispenser.RestrictedItemInputDispenserHandler;
import teamzesa.Event.Restricted.AntiExploit.Interaction.LeverInteraction.LeverInteractionHandler;
import teamzesa.Event.Restricted.AntiExploit.Interaction.PlayerInteraction.RestrictedCommandInteraction;
import teamzesa.Event.Restricted.AntiExploit.Interaction.PlayerInteraction.RestrictedPlayerInteract;
import teamzesa.Event.Restricted.AntiExploit.Interaction.SignInteraction.RestrictedSignChangeInteraction;
import teamzesa.Event.Restricted.AntiExploit.Interaction.SignInteraction.RestrictedSignOpenInteraction;
import teamzesa.Event.Restricted.Function.Event.EntityExplode.RestrictedExplosiveDamageManager;
import teamzesa.Event.Restricted.Function.TotemStack.RestrictedShulkerChest;
import teamzesa.Event.Restricted.Function.TotemStack.RestrictedStackingTotemInteraction;
import teamzesa.Event.UserInterface.Function.Executor.UICloser;
import teamzesa.Event.UserInterface.Function.Executor.UIExecutor;

/**
 * {@linkplain org.bukkit.event.Listener}의 구현체를 관리합니다.
 *
 * @performance RunTime시 해당 클래스는 하나의 Instance만 생성됩니다.
 * <p>
 * 개발 진행 시 필요에 따라 각 태그를 사용하여 기록합니다.
 * todo
 * fixme
 * refactoring
 * Implement
 * methodImplement
 * funImplement
 * debug
 * <p>
 * {@link EventPriority}로 모든 이벤트의 우선순위를 정합니다.
 * Event 처리의 우선순위는 역순이며, 자세한건 {@linkplain EventHandler}, {@link EventPriority}를 참고해주세요.
 * {@link EventHandler#ignoreCancelled()}는 이벤트 취소 시 더 이상 Runtime에서 호출하지 않습니다. {@link EventHandler} 참고해주세요.
 * <p>
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
        new ImportPlayerStatus(event);
        new JoinMessageHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        new PlayTimeUpdate(event);
        new QuitMessageHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void RaidTriggerEvent(RaidTriggerEvent event) {
//        todo 반야생변경에 따른 콘텐츠 수정
        new RaidAnnouncerEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        new LifeSteelEvent(event);
        new IncreaseKillCountHandler(event);
        new DropDeadsHeadEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDeathEvent(EntityDeathEvent event) {
        new BossDeathRewardHandler(event);
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
        new RespawnMessageHandler(event);
        new PlayerFlyEnableEvent(event);
        new RespawnRandomTeleportEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryClickEvent(InventoryClickEvent event) {

//        Restriction
        new RestrictedItemInputDispenserHandler(event);
        new RestrictedStackingTotemInteraction(event);
        new RestrictedShulkerChest(event);

//        Enhance Prepare
        new UpdateEnhanceResultItemLoreFromAnvil(event);
        new UpdateEnhanceResultItemLoreFromGrindStone(event);

//        User Interface
        new UIExecutor(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryCloseEvent(InventoryCloseEvent event) {
//        User Interface
        new UICloser(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerEditSign(PlayerOpenSignEvent event) {
        new RestrictedSignOpenInteraction(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void SignChangeEvent(SignChangeEvent event) {
        new RestrictedSignChangeInteraction(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryMoveItemEvent(InventoryMoveItemEvent event) {
        new RestrictedDispenserInventoryMoveItemHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityExplodeEvent(EntityExplodeEvent event) {
        new ExplosiveEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerArmSwingEvent(PlayerArmSwingEvent event) {
        new DualWieldSwingHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDamageEvent(EntityDamageEvent event) {
//        Event Cancelled 하면 해당 Event 자체가 캔슬됌.
        new EnhanceArmourResistanceArmour(event);
        new RestrictedExplosiveDamageManager(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        new LeverInteractionHandler(event);
        new RestrictedPlayerInteract(event);
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
        new RestrictedCommandInteraction(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void AsyncChatEvent(AsyncChatEvent event) {
        new RestrictedChatFlood(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        new MainMenuUI(event);
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
