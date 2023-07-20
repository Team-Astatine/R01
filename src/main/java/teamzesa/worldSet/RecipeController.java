package teamzesa.worldSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.ComponentExchanger;

import java.util.HashSet;
import java.util.Set;

public class RecipeController extends ComponentExchanger implements Listener {
    private Set<Material> lockingStuff;

    public RecipeController() {
        this.lockingStuff = new HashSet<>();
//    define BanItem
        this.lockingStuff.add(Material.TNT);
        this.lockingStuff.add(Material.TNT_MINECART);
    }

    private boolean stuffChecking(ItemStack item) {
        return lockingStuff.stream()
                .noneMatch(banStuff -> banStuff == item.getType());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCraft(CraftItemEvent e) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack item = e.getCurrentItem();

                if (stuffChecking(item))
                    return;

                Player player = (Player)e.getWhoClicked();
                player.sendMessage(componentSet("해당 아이템은 조합할 수 없습니다.","RED"));
                e.setCancelled(true);
            }
        };

        task.run();
    }
}
