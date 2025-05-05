package teamzesa.Event.Restricted;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import teamzesa.Event.EventRegister.EventRegister;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.Restricted.AntiExploit.ItemAndCommand.RestrictedElement;
import teamzesa.Util.StringComponentExchanger;

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
        boolean restrictedItem = new RestrictedElement().restrictedItem.stream()
                .anyMatch(this.currentItemMaterial::equals);

        if (BooleanUtils.isFalse(restrictedItem))
            return;

        Player player = (Player) this.event.getWhoClicked();
        this.event.setCancelled(true);
        playerSendMsgComponentExchanger(player, "해당 아이템은 조합할 수 없습니다.", ColorType.RED);
    }
}
