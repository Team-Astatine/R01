package teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.Map;
import java.util.Optional;

public class UpdateEnhanceItemLoreFromEnchantment extends StringComponentExchanger implements EventRegister {
    private Map<Enchantment, Integer> enchantment;
    private ItemStack enchantItem;
    private int sharpnessLevel = 0;
    private int enhanceLevel = 0;

    private final EnchantItemEvent event;

    public UpdateEnhanceItemLoreFromEnchantment(EnchantItemEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.enchantItem = this.event.getItem();
        this.enchantment = this.event.getEnchantsToAdd();
    }

    @Override
    public void execute() {
        Optional.ofNullable(this.enchantment.get(Enchantment.SHARPNESS)).ifPresent(
                level -> this.sharpnessLevel = level
        );

        if (this.sharpnessLevel < 1)
            return;

        if (BooleanUtils.isFalse(this.enchantItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.enchantItem.getItemMeta().hasCustomModelData()))
            return;

        this.enchantItem.addEnchantments(this.enchantment);
        this.enhanceLevel = this.enchantItem.getItemMeta().getCustomModelData();

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
