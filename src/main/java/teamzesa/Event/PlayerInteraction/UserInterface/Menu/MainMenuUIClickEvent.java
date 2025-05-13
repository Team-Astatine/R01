package teamzesa.Event.PlayerInteraction.UserInterface.Menu;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import teamzesa.Event.EventRegister;
import teamzesa.Util.Function.StringComponentExchanger;
import teamzesa.Util.UserUIGenerator.Interface.UIHolder;

public class MainMenuUIClickEvent extends StringComponentExchanger implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private final InventoryClickEvent event;

    public MainMenuUIClickEvent(InventoryClickEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.uiHolder = this.event.getClickedInventory().getHolder() instanceof UIHolder holder ? holder : null;
        this.clickPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.uiHolder))
            return;

        if (ObjectUtils.notEqual(this.clickPlayer, this.uiHolder.getOwner()))
            return;

        switch (this.event.getSlot()) {
            case 0,1,9,10 -> this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

            case 3,4,5,12,13,14 -> this.clickPlayer.performCommand("shop");

            case 7,8,16,17 -> this.clickPlayer.performCommand("강화");

            case 18,19,27,28 -> this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

            case 21,22,23,30,31,32 -> this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

            case 25,26,34,35 -> this.clickPlayer.performCommand("gsitactioui");

            default -> this.event.setCancelled(true);
        }

        this.event.setCancelled(true);
    }
}
