package teamzesa.event.Enhance.PlayerInteraction.UserInterface;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.enhance.EnhanceInventoryHandler;
import teamzesa.event.EventRegister.EventRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static teamzesa.DataBase.enhance.EnhanceInventoryHandler.*;

public class EnhanceUICloseEvent implements EventRegister {
    private final int MINIMUM_INVENTORY_SLOT = 5;
    private EnhanceInventoryHandler enhanceInventoryHandler;

    private Player itemOwner;
    private Inventory enhanceDialog;
    private ArrayList<ItemStack> slot;

    private final InventoryCloseEvent event;

    public EnhanceUICloseEvent(InventoryCloseEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.enhanceInventoryHandler = EnhanceInventoryHandler.getEnhanceInventoryHandler();

        this.itemOwner = (Player) this.event.getPlayer();
        this.enhanceDialog = this.enhanceInventoryHandler.get(this.itemOwner.getUniqueId());
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

        long blankInventoryCount = Arrays.stream(this.itemOwner.getInventory().getContents())
                .filter(Objects::isNull)
                .count();

        if (blankInventoryCount >= MINIMUM_INVENTORY_SLOT + receiveItemCount)
            this.slot.stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> this.itemOwner.getInventory().addItem(item));
        else
            this.slot.stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> this.itemOwner.getWorld().dropItem(this.itemOwner.getLocation(), item));

        this.enhanceInventoryHandler.remove(this.itemOwner.getUniqueId());
    }

    private boolean isNotAllowedThisEvent() {
        if (BooleanUtils.isFalse(this.event.getInventory().equals(this.enhanceDialog)))
            return true;

        ItemStack slotZeroItem = this.event.getView().getItem(0);
        ItemStack slotFirstItem = this.event.getView().getItem(1);
        ItemStack slotSecondItem = this.event.getView().getItem(2);

        ItemStack slotSixItem = this.event.getView().getItem(6);
        ItemStack slotSevenItem = this.event.getView().getItem(7);
        ItemStack slotEightItem = this.event.getView().getItem(8);

        if (ObjectUtils.notEqual(slotZeroItem.getItemMeta().getCustomModelData(), PANEL_STUFF_CUSTOM_DATA))
            return true;

        if (ObjectUtils.notEqual(slotFirstItem.getItemMeta().getCustomModelData(), PANEL_STUFF_CUSTOM_DATA))
            return true;

        if (ObjectUtils.notEqual(slotSecondItem.getItemMeta().getCustomModelData(), PANEL_STUFF_CUSTOM_DATA))
            return true;

        if (ObjectUtils.notEqual(slotSixItem.getItemMeta().getCustomModelData(), EXECUTE_DISCORD_DATA))
            return true;

        if (ObjectUtils.notEqual(slotSevenItem.getItemMeta().getCustomModelData(), EXECUTE_STUFF_DATA))
            return true;

        return ObjectUtils.notEqual(slotEightItem.getItemMeta().getCustomModelData(), EXECUTE_NOTION_DATA);
    }
}