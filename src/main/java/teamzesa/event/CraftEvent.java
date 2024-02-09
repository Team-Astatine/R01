package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;

import java.util.HashSet;
import java.util.Set;

public class CraftEvent extends ComponentExchanger implements EventRegister {
    private Material currentStuff;
    private Set<Material> lockingStuff;
    private final CraftItemEvent event;

    public CraftEvent(CraftItemEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentStuff = this.event.getCurrentItem().getType();
        this.lockingStuff = new HashSet<>();
        this.lockingStuff.add(Material.TNT);
        this.lockingStuff.add(Material.TNT_MINECART);
    }

    @Override
    public void execute() {
        boolean typeChecker = this.lockingStuff.stream()
                .anyMatch(banningStuffMaterial -> banningStuffMaterial.equals(this.currentStuff));

        if (typeChecker) {
            Player player = (Player) this.event.getWhoClicked();
            playerSendMsgComponentExchanger(player, "해당 아이템은 조합할 수 없습니다.", ColorList.RED);
            this.event.setCancelled(true);
        }
    }
}
