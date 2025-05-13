package teamzesa.Event.UserInterface.Function.Interface;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public interface UIHolder extends InventoryHolder {
    Player getOwner();

    void UIExecutor();
}
