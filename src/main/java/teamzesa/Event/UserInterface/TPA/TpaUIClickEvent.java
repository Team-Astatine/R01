package teamzesa.Event.UserInterface.TPA;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.Event.EventRegister;
import teamzesa.Event.UserInterface.Function.Interface.UIHolder;
import teamzesa.Util.Function.StringComponentExchanger;

public class TpaUIClickEvent extends StringComponentExchanger implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private final InventoryClickEvent event;

    public TpaUIClickEvent(InventoryClickEvent event) {
        this.event = event;

        if (ObjectUtils.isEmpty(this.event.getClickedInventory()))
            return;

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

        ItemStack targetPlayerHead = this.event.getClickedInventory().getItem(this.event.getSlot());
        String targetPlayerName = componentExchanger(targetPlayerHead.displayName());

        this.event.setCancelled(true);
        if (targetPlayerName.isBlank())
            return;

        switch (event.getClick()) {
            case ClickType.LEFT -> sendTPA("tpa", targetPlayerName);
            case ClickType.RIGHT -> sendTPA("tpahere", targetPlayerName);
        }

        this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }

    private void sendTPA(String tpa, String targetPlayerName) {
        this.clickPlayer.performCommand(String.format("%s %s", tpa, targetPlayerName));
    }
}
