package teamzesa.event.Restricted;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.Arrays;
import java.util.Objects;

public class RestrictedPreparedItemCrafterEvent extends StringComponentExchanger implements EventRegister {

    private Player player;
    private Inventory clickerInventory;
    private Inventory currentOpeningContainerInventory;
    private final InventoryClickEvent event;

    public RestrictedPreparedItemCrafterEvent(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = (Player) this.event.getWhoClicked();
        this.clickerInventory = this.event.getClickedInventory();
        this.currentOpeningContainerInventory = this.event.getInventory();
    }

    @Override
    public void execute() {
        System.out.println("null check");
        if (this.clickerInventory == null)
            return;

//        System.out.println("getClickedInv > " + this.playerInventory.getType());
//        System.out.println("getInv > " + this.currentOpeningContainerInventory.getType());

        System.out.println("current click container Typing Check");
        if (this.currentOpeningContainerInventory.getType() != InventoryType.CRAFTER)
            return;

        System.out.println("clicker container Typing Check");
        if (this.clickerInventory.getType() != InventoryType.PLAYER)
            return;

        boolean isAllowedItem = Arrays.stream(this.currentOpeningContainerInventory.getContents())
                .filter(Objects::nonNull)
                .anyMatch(item ->
                        item.getType().equals(Material.TNT)
                    ||  item.getType().equals(Material.SAND)
                    ||  item.getType().equals(Material.GUNPOWDER)
                );

        if (!isAllowedItem)
            return;

        System.out.println("cancel");
        this.event.setCancelled(true);
        playerSendMsgComponentExchanger(player, "해당 아이템은 크래프터에 넣을 수 없습니다.", ColorMap.RED);
    }
}
