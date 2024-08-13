package teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.Map;
import java.util.Optional;

public class UpdateEnhanceResultItemLoreFromGrindStone extends StringComponentExchanger implements EventRegister {
    private ItemStack enchantItem;

    private final InventoryClickEvent event;

    public UpdateEnhanceResultItemLoreFromGrindStone(InventoryClickEvent event) {
        this.event = event;

        if (valid())
            return;

        init();
        execute();
    }

    public boolean valid() {
        if (this.event.getClickedInventory() == null)
            return true;

        if (BooleanUtils.isFalse(this.event.getClickedInventory().getType().equals(InventoryType.GRINDSTONE)))
            return true;

        return BooleanUtils.isFalse(this.event.getSlotType().equals(InventoryType.SlotType.RESULT));
    }

    @Override
    public void init() {
        this.enchantItem = this.event.getClickedInventory().getItem(2);
    }

    @Override
    public void execute() {
        if (this.enchantItem == null) //아이템에 아무것도 옵션이 없을때 null 뜸
            return;

        if (BooleanUtils.isFalse(this.enchantItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.enchantItem.getItemMeta().hasCustomModelData()))
            return;

        Map<Enchantment, Integer> enchantment = this.enchantItem.getEnchantments();
        Optional.of(enchantment).ifPresent(
                enchant -> this.enchantItem.addEnchantments(enchant)
        );

        int enhanceLevel = this.enchantItem.getItemMeta().getCustomModelData();
        ItemMeta targetItemMeta = this.enchantItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        this.enchantItem.setItemMeta(targetItemMeta);

        try {
            EnhanceUtil.increaseDmgAndAddLore(this.enchantItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
