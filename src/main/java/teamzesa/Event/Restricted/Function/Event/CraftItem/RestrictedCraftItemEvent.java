package teamzesa.Event.Restricted.Function.Event.CraftItem;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import teamzesa.Event.EventRegister;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.Restricted.AntiExploit.RestrictedElement;
import teamzesa.Util.Function.StringComponentExchanger;

public class RestrictedCraftItemEvent extends StringComponentExchanger implements EventRegister {
    private Material currentItemMaterial;

    private final CraftItemEvent event;

    public RestrictedCraftItemEvent(CraftItemEvent event) {
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
