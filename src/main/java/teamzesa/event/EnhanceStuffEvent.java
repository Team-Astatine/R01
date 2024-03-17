package teamzesa.event;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;

public class EnhanceStuffEvent extends StringComponentExchanger implements EventRegister {
    private Player player;
    private ItemStack cube;
    private ItemStack targetItem;
    private final PlayerInventorySlotChangeEvent event;

    public EnhanceStuffEvent(PlayerInventorySlotChangeEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        /*
            새로운 아이템 -> 주문서
            기존 아이템 -> 강화템
        */
        this.player = this.event.getPlayer();
        this.cube = this.event.getNewItemStack();
        this.targetItem = this.event.getOldItemStack();
    }

    @Override
    public void execute() {

    }
}
