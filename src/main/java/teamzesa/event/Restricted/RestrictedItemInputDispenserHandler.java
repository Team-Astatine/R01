package teamzesa.event.Restricted;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Interface.StringComponentExchanger;

public class RestrictedItemInputDispenserHandler extends StringComponentExchanger implements EventRegister {

    private Player player;
    private ItemStack itemStack;
    private Inventory clickerInventory;
    private Inventory currentOpeningContainerInventory;
    private final InventoryClickEvent event;

    public RestrictedItemInputDispenserHandler(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = (Player) this.event.getWhoClicked();
        this.itemStack = this.event.getCurrentItem();
        this.clickerInventory = this.event.getClickedInventory();
        this.currentOpeningContainerInventory = this.event.getInventory();
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.clickerInventory))
            return;

        if (ObjectUtils.notEqual(this.currentOpeningContainerInventory.getType(), InventoryType.DISPENSER))
            return;

        if (ObjectUtils.notEqual(this.clickerInventory.getType(), InventoryType.PLAYER))
            return;

        if (this.player.isOp())
            return;

        Material playerInputItemMaterial = this.itemStack.getType();
        boolean isRestrictedItem = new RestrictedElement().restrictedItem.stream()
                .anyMatch(playerInputItemMaterial::equals);

        if (BooleanUtils.isFalse(isRestrictedItem))
            return;

        this.event.setCancelled(true);
        playerSendMsgComponentExchanger(player, "해당 아이템은 디스펜서에 넣을 수 없습니다.", ColorMap.RED);
    }
}
