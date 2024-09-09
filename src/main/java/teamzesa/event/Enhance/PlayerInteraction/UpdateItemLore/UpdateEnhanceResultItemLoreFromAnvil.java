package teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;

public class UpdateEnhanceResultItemLoreFromAnvil extends StringComponentExchanger implements EventRegister {
    private ItemStack resultItem;

    private final InventoryClickEvent event;

    public UpdateEnhanceResultItemLoreFromAnvil(InventoryClickEvent event) {
        this.event = event;

        if (valid())
            return;

        init();
        execute();
    }

    public boolean valid() {
        if (ObjectUtils.isEmpty(this.event.getClickedInventory()))
            return true;

        if (BooleanUtils.isFalse(this.event.getClickedInventory().getType().equals(InventoryType.ANVIL)))
            return true;

        return BooleanUtils.isFalse(this.event.getSlotType().equals(InventoryType.SlotType.RESULT));
    }

    @Override
    public void init() {
        this.resultItem = this.event.getClickedInventory().getItem(2);
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.resultItem))
            return;

        if (BooleanUtils.isFalse(this.resultItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.resultItem.getItemMeta().hasCustomModelData()))
            return;

        if (this.resultItem.getEnchantmentLevel(Enchantment.SHARPNESS) < 1)
            return;

        int enhanceLevel = this.resultItem.getItemMeta().getCustomModelData();

        ItemMeta targetItemMeta = this.resultItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        this.resultItem.setItemMeta(targetItemMeta);

        this.resultItem.addEnchantments(this.resultItem.getEnchantments());

        try {
            EnhanceUtil.updateEnhanceItemLore(this.resultItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
