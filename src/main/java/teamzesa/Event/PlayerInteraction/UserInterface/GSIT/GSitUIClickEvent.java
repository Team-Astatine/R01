package teamzesa.Event.PlayerInteraction.UserInterface.GSIT;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import teamzesa.Event.EventRegister;
import teamzesa.Util.Function.StringComponentExchanger;

public class GSitUIClickEvent extends StringComponentExchanger implements EventRegister {
    private InventoryHolder inventoryHolder;
    private Player clickPlayer;
    private Player holderPlayer;

    private final InventoryClickEvent event;

    public GSitUIClickEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.inventoryHolder = this.event.getClickedInventory().getHolder();

        this.clickPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
        this.holderPlayer = this.event.getClickedInventory().getHolder() instanceof Player player ? player : null;
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.inventoryHolder))
            return;

        if (ObjectUtils.notEqual(this.clickPlayer, this.holderPlayer))
            return;

        switch (this.event.getSlot()) {
            case 1 -> this.clickPlayer.performCommand("sit");
            case 3 -> this.clickPlayer.performCommand("lay");
            case 5 -> this.clickPlayer.performCommand("spin");
            case 7 -> this.clickPlayer.performCommand("crawl");
        }

        this.event.setCancelled(true);
        this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }
}
