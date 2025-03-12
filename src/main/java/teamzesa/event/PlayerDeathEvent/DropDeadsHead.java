package teamzesa.event.PlayerDeathEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import teamzesa.event.EventRegister.EventRegister;

public class DropDeadsHead implements EventRegister {

    private Player player;
    private final PlayerDeathEvent event;

    public DropDeadsHead(PlayerDeathEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.player = event.getPlayer();
    }

    @Override
    public void execute() {
        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta deadHead = (SkullMeta) headItemStack.getItemMeta();

        deadHead.setOwningPlayer(event.getPlayer());
        headItemStack.setItemMeta(deadHead);

        event.getDrops().add(headItemStack);
    }
}
