package teamzesa.event;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Interface.EventRegister;
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
        if (!this.cube.getType().equals(Material.DRAGON_HEAD))
            return;

        if (!this.targetItem.getType().equals(Material.NETHERITE_SWORD))
            return;

//        강화구현
        this.cube.setAmount(this.cube.getAmount() - 1);
        this.targetItem.setCustomModelData(1);


        System.out.println("slot index > " + this.event.getSlot());;
        System.out.println("targetItemInfo > " + this.targetItem);


//        methodImplement
//        손에 든 아이템이 증발해버림
        this.player.getInventory().setItem(this.event.getSlot(), null);
//        this.player.getInventory().setItem(this.event.getSlot(), this.targetItem);

//        List<Component> targetItemLore = new ArrayList<>(this.targetItem.lore());
//        targetItemLore.add(componentExchanger("1강",ColorList.GREEN));
//        this.targetItem.lore(targetItemLore);
    }
}
