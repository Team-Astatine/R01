package teamzesa.event;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;

public class EnhanceStuffEvent extends ComponentExchanger implements EventRegister {
    private Action action;
    private Player player;
    private ItemStack itemStack;
    private final PlayerInventorySlotChangeEvent event;

    public EnhanceStuffEvent(PlayerInventorySlotChangeEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        System.out.println("getHandlers > " + this.event.getHandlers());
//        funImplement
        /*
        새로운 아이템 -> 주문서
        기존 아이템 -> 강화템
         */
        System.out.println("getNewItemStack > " + this.event.getNewItemStack());
        System.out.println("getOldItemStack > " + this.event.getOldItemStack());

        System.out.println("getRawSlot > " + this.event.getRawSlot());
        System.out.println("getSlot > " + this.event.getSlot());
    }
}
