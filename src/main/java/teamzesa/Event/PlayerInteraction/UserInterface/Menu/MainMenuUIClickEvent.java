package teamzesa.Event.PlayerInteraction.UserInterface.Menu;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import teamzesa.Event.EventRegister;
import teamzesa.Util.Function.StringComponentExchanger;

public class MainMenuUIClickEvent extends StringComponentExchanger implements EventRegister {

    private InventoryHolder inventoryHolder;
    private Player clickPlayer;
    private Player holderPlayer;

    private InventoryClickEvent event;

    public MainMenuUIClickEvent(InventoryClickEvent event) {
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
            case 0,1,9,10 -> {

            }

            case 3,4,5,12,13,14 -> {

            }

            case 7,8,16,17 -> {

            }

            case 18,19,27,28 -> {

            }

            case 21,22,23,30,31,32 -> {

            }

            case 25,26,34,35 -> {

            }
        }

        this.event.setCancelled(true);
        this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }
}
