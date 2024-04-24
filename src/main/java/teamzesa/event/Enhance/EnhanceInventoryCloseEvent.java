package teamzesa.event.Enhance;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;

import java.util.Arrays;
import java.util.List;

public class EnhanceInventoryCloseEvent implements EventRegister {

    private Player player;
    private ItemStack weaponItem;
    private ItemStack scrollItem;
    private ItemStack scrollProtectItem;
    private InventoryCloseEvent event;
    public EnhanceInventoryCloseEvent(InventoryCloseEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer().getKiller();
        this.weaponItem = this.event.getInventory().getItem(3);
        this.scrollItem = this.event.getInventory().getItem(4);
        this.scrollProtectItem = this.event.getInventory().getItem(5);
    }

    @Override
    public void execute() {
        int cnt = 0;
        ItemStack[] currentInv = this.player.getInventory().getContents();
        for (int i = 0; i < currentInv.length; i++) {
            if (currentInv[i] == null)
                cnt++;
        }

        if (cnt < 1) {


        }
    }
}
