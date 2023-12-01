package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.ThreadPool;
import teamzesa.util.Enum.ColorList;

import java.util.HashSet;
import java.util.Set;

public class RecipeController extends ComponentExchanger implements Listener {
    private final Set<Material> lockingStuff;

    public RecipeController() {
        this.lockingStuff = new HashSet<>();
//    define BanItem
        this.lockingStuff.add(Material.TNT);
        this.lockingStuff.add(Material.TNT_MINECART);
    }

    private boolean stuffChecking(ItemStack item) {
        return lockingStuff.stream()
                .noneMatch(banStuff -> item.getType().equals(banStuff));
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public synchronized void onCraft(CraftItemEvent e) {
        ItemStack item = e.getCurrentItem();

        if (stuffChecking(item))
            return;

        Player player = (Player)e.getWhoClicked();
        playerSendMsgComponentExchanger(player,"해당 아이템은 조합할 수 없습니다.", ColorList.RED);
        e.setCancelled(true);
    }
}
