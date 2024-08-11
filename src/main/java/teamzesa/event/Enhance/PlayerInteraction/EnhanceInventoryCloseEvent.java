package teamzesa.event.Enhance.PlayerInteraction;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static teamzesa.command.EnhanceDialog.*;

public class EnhanceInventoryCloseEvent implements EventRegister {
    private final int MINIMUM_INVENTORY_SLOT = 5;

    private Player itemOwner;
    private ArrayList<ItemStack> slot;

    private final InventoryCloseEvent event;

    public EnhanceInventoryCloseEvent(InventoryCloseEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.itemOwner = (Player) this.event.getPlayer();
        this.slot = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (validation())
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
    }

    private boolean validation() {
        if (BooleanUtils.isFalse(this.event.getInventory().equals(ENHANCE_DIALOG)))
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