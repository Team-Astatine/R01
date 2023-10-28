package teamzesa.worldSet;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.ComponentExchanger;
import teamzesa.ThreadPool;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class RecipeController extends ComponentExchanger implements Listener {
    private final Set<Material> lockingStuff;
    private final ThreadPool threadPool;

    public RecipeController() {
        threadPool = ThreadPool.getThreadPool();

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
                ComponentExchanger.playerAnnouncer(player,"해당 아이템은 조합할 수 없습니다.",Color.RED);
            }
        };
        task.run();
//        threadPool.addTask(task);
//        threadPool.executorServiceOff();
        e.setCancelled(true);
    }
}
