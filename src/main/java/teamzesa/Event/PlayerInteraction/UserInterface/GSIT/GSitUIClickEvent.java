package teamzesa.Event.PlayerInteraction.UserInterface.GSIT;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import teamzesa.Event.EventRegister;
import teamzesa.Util.Function.StringComponentExchanger;

public class GSitUIClickEvent extends StringComponentExchanger implements EventRegister {
    private final InventoryClickEvent event;
    private Player ownerPlayer;
    private Inventory gsitInventory;

    public GSitUIClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.ownerPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.gsitInventory = GSitInventoryManager.getGSitInventoryManager().get(this.ownerPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        if (BooleanUtils.isFalse(this.event.getInventory().equals(this.gsitInventory))) {
            return;
        }

        Inventory playerOpenInv = this.event.getClickedInventory();
        if (ObjectUtils.isNotEmpty(playerOpenInv)) {
            switch (this.event.getSlot()) {
                case 1 -> this.ownerPlayer.performCommand("sit");
                case 3 -> this.ownerPlayer.performCommand("lay");
                case 5 -> this.ownerPlayer.performCommand("spin");
                case 7 -> this.ownerPlayer.performCommand("crawl");
            }
        }

        this.event.setCancelled(true);
        this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
        GSitInventoryManager.getGSitInventoryManager().remove(this.ownerPlayer.getUniqueId());
    }
}
