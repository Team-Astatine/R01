package teamzesa.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import teamzesa.util.ComponentExchanger;

public class Menu extends ComponentExchanger implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void clickSwapInv(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
//        vaild MenuBar Event
        if (!player.isSneaking())
            return;
        e.setCancelled(true);

        Inventory inventory = Bukkit.createInventory(player, 9, "강화 시스템");
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInv(InventoryClickEvent e) {
        e.getWhoClicked();
    }
}
