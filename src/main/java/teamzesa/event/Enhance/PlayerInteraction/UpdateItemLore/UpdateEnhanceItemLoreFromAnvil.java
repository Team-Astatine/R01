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

public class UpdateEnhanceItemLoreFromAnvil extends StringComponentExchanger implements EventRegister {

    private final InventoryClickEvent event;

    public UpdateEnhanceItemLoreFromAnvil(InventoryClickEvent event) {
        this.event = event;
        init();
        execute();
    }


    @Override
    public void init() {

    }

    @Override
    public void execute() {
        if (BooleanUtils.isFalse(this.event.getClickedInventory().getType().equals(InventoryType.ANVIL)))
            return;

        if (BooleanUtils.isFalse(this.event.getSlotType().equals(InventoryType.SlotType.RESULT)))
            return;

        ItemStack enchantItem = this.event.getClickedInventory().getItem(2);
        Map<Enchantment, Integer> enchantment = enchantItem.getEnchantments();
        int sharpnessLevel = enchantItem.getEnchantmentLevel(Enchantment.SHARPNESS);

        if (sharpnessLevel < 1)
            return;

        if (BooleanUtils.isFalse(enchantItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(enchantItem.getItemMeta().hasCustomModelData()))
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
