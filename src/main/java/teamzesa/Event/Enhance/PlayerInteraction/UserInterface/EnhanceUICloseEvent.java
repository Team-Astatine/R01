package teamzesa.Event.Enhance.PlayerInteraction.UserInterface;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import teamzesa.Util.UserUIGenerator.UIGenerator.InventoryUICustomModeData;
import teamzesa.Event.EventRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EnhanceUICloseEvent implements EventRegister {
    private final int MINIMUM_INVENTORY_SLOT = 3;
    private ArrayList<ItemStack> slot;

    private InventoryHolder inventoryHolder;
    private Player closePlayer;
    private Player holderPlayer;

    private final InventoryCloseEvent event;

    public EnhanceUICloseEvent(InventoryCloseEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.inventoryHolder = this.event.getInventory().getHolder();

        this.closePlayer = this.event.getPlayer() instanceof Player player ? player : null;
        this.holderPlayer = this.event.getInventory().getHolder() instanceof Player player ? player : null;

        this.slot = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (isNotAllowedThisEvent())
            return;

        this.slot.add(this.event.getInventory().getItem(3));
        this.slot.add(this.event.getInventory().getItem(4));
        this.slot.add(this.event.getInventory().getItem(5));

        long receiveItemCount = this.slot.stream()
                .filter(Objects::nonNull)
                .count();

        long playerFreeSlotCount = Arrays.stream(this.closePlayer.getInventory().getContents())
                .filter(Objects::isNull)
                .count();

        if (playerFreeSlotCount >= MINIMUM_INVENTORY_SLOT + receiveItemCount)
            this.slot.stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> this.closePlayer.getInventory().addItem(item));
        else
            this.slot.stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> this.closePlayer.getWorld().dropItem(this.closePlayer.getLocation(), item));
    }

    private boolean isNotAllowedThisEvent() {
        if (ObjectUtils.isEmpty(this.inventoryHolder))
            return true;

        if (ObjectUtils.notEqual(this.closePlayer, this.holderPlayer))
            return true;

        ItemStack slotZeroItem = this.event.getView().getItem(0);
        ItemStack slotFirstItem = this.event.getView().getItem(1);
        ItemStack slotSecondItem = this.event.getView().getItem(2);

        ItemStack slotSixItem = this.event.getView().getItem(6);
        ItemStack slotSevenItem = this.event.getView().getItem(7);
        ItemStack slotEightItem = this.event.getView().getItem(8);

        if (ObjectUtils.notEqual
                (slotZeroItem.getItemMeta().getCustomModelData(), InventoryUICustomModeData.PANEL_STUFF_CUSTOM_DATA.getValues())
        )
            return true;

        if (ObjectUtils.notEqual
                (slotFirstItem.getItemMeta().getCustomModelData(), InventoryUICustomModeData.PANEL_STUFF_CUSTOM_DATA.getValues())
        )
            return true;

        if (ObjectUtils.notEqual
                (slotSecondItem.getItemMeta().getCustomModelData(), InventoryUICustomModeData.PANEL_STUFF_CUSTOM_DATA.getValues())
        )
            return true;

        if (ObjectUtils.notEqual
                (slotSixItem.getItemMeta().getCustomModelData(), InventoryUICustomModeData.EXECUTE_DISCORD_DATA.getValues())
        )
            return true;

        if (ObjectUtils.notEqual
                (slotSevenItem.getItemMeta().getCustomModelData(), InventoryUICustomModeData.EXECUTE_STUFF_DATA.getValues())
        )
            return true;

        return ObjectUtils.notEqual(
                slotEightItem.getItemMeta().getCustomModelData(), InventoryUICustomModeData.EXECUTE_NOTION_DATA.getValues()
        );
    }
}