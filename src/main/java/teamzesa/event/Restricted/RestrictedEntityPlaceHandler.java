package teamzesa.event.Restricted;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Interface.StringComponentExchanger;

public class RestrictedEntityPlaceHandler extends StringComponentExchanger implements EventRegister {
    private final PlayerInteractEvent event;

    public RestrictedEntityPlaceHandler(PlayerInteractEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        if (this.event.getHand() != EquipmentSlot.HAND)
            return;

        if (this.event.getPlayer().isOp())
            return;

        if (!this.event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.ARMOR_STAND))
            return;

        this.event.setCancelled(true);
        playerSendMsgComponentExchanger(this.event.getPlayer(), "아머스탠드는 설치할 수 없습니다.", ColorMap.RED);
    }
}
