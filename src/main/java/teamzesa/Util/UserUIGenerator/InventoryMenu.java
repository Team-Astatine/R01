package teamzesa.Util.UserUIGenerator;


import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import teamzesa.Data.Function.UserInterface.SlotItemMapping;

import java.util.List;

/**
 * 인벤토리 메뉴를 생성하기 위한 인터페이스 입니다.
 * 인벤토리 생성부분을 builder pattern 으로 구현하고, 내부 창고 속성값을 수정합니다.
 */
public interface InventoryMenu {
    InventoryMenu chestOwner(Player chestOwner);
    InventoryMenu setInventory(InventoryType inventoryType, Component component);
    InventoryMenu setInventory(int slotCount, Component component);
    InventoryMenu setEnhanceUIItem(List<SlotItemMapping> slotItemMappings);
    Inventory executeUI();
}
