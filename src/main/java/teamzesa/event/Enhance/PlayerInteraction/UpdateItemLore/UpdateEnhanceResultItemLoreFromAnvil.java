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

public class UpdateEnhanceResultItemLoreFromAnvil extends StringComponentExchanger implements EventRegister {
    private ItemStack enchantItem;
    private Map<Enchantment, Integer> enchantment;
    private int sharpnessLevel;

    private final InventoryClickEvent event;

    public UpdateEnhanceResultItemLoreFromAnvil(InventoryClickEvent event) {
        this.event = event;

        if (valid())
            return;

        init();
        execute();
    }

    public boolean valid() {
        if (this.event.getClickedInventory() == null)
            return true;

        if (BooleanUtils.isFalse(this.event.getClickedInventory().getType().equals(InventoryType.ANVIL)))
            return true;

        return BooleanUtils.isFalse(this.event.getSlotType().equals(InventoryType.SlotType.RESULT));
    }

    @Override
    public void init() {
        this.enchantItem = this.event.getClickedInventory().getItem(2);
        this.enchantment = this.enchantItem.getEnchantments();
        this.sharpnessLevel = enchantItem.getEnchantmentLevel(Enchantment.SHARPNESS);
    }

    @Override
    public void execute() {
        if (BooleanUtils.isFalse(enchantItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(enchantItem.getItemMeta().hasCustomModelData()))
            return;

        if (sharpnessLevel < 1)
            return;

        enchantItem.addEnchantments(enchantment);

        int enhanceLevel = enchantItem.getItemMeta().getCustomModelData();
        ItemMeta targetItemMeta = enchantItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        enchantItem.setItemMeta(targetItemMeta);

        try {
            EnhanceUtil.increaseDmgAndAddLore(enchantItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
