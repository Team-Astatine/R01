package teamzesa.event.Restricted;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;

public class RestrictedItemCraftHandler extends StringComponentExchanger implements EventRegister {
    private Material currentItemMaterial;

    private final CraftItemEvent event;
    public RestrictedItemCraftHandler(CraftItemEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.currentItemMaterial = this.event.getCurrentItem().getType();
    }

    @Override
    public void execute() {
        boolean isRestrictedItem = new RestrictedElement().restrictedItem.stream()
                .anyMatch(this.currentItemMaterial::equals);

        if (BooleanUtils.isFalse(isRestrictedItem))
            return;

        Player player = (Player) this.event.getWhoClicked();
        this.event.setCancelled(true);
        playerSendMsgComponentExchanger(player, "해당 아이템은 조합할 수 없습니다.", ColorMap.RED);
    }
}
