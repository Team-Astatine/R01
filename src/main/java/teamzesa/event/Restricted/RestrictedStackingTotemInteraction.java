package teamzesa.event.Restricted;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Interface.StringComponentExchanger;

public class RestrictedStackingTotemInteraction extends StringComponentExchanger implements EventRegister {

    private Player clicker;
    private ItemStack offHandItem;
    private ItemStack getCurrentCursorHoldItem;
    public final InventoryClickEvent event;

    public RestrictedStackingTotemInteraction(InventoryClickEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.clicker = (Player) this.event.getWhoClicked();
        this.offHandItem = this.clicker.getInventory().getItemInOffHand();
        this.getCurrentCursorHoldItem = this.event.getCurrentItem();
    }

    @Override
    public void execute() {
        System.out.println(1);
        if (ObjectUtils.notEqual(this.getCurrentCursorHoldItem, EquipmentSlot.OFF_HAND))
            return;

        System.out.println(2);
        if (ObjectUtils.notEqual(this.offHandItem.getType(), Material.TOTEM_OF_UNDYING))
            return;

        System.out.println(3);
        if (this.offHandItem.getAmount() < 2)
            return;

        System.out.println(4);
        playerSendMsgComponentExchanger(this.clicker, "뭉쳐진 토템은 스왑키로만 이동할 수 있습니다.", ColorMap.RED);
        this.event.setCancelled(true);
    }
}
