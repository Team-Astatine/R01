package teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.event.Enhance.Interface.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class UpdateEnhanceItemPrepareAnvil implements EventRegister {

    private ItemStack resultItem;

    private final PrepareAnvilEvent event;

    public UpdateEnhanceItemPrepareAnvil(PrepareAnvilEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void execute() {
        this.resultItem = this.event.getInventory().getItem(2);
    }

    @Override
    public void init() {
        if (this.resultItem == null)
            return;

        if (BooleanUtils.isFalse(this.resultItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.resultItem.getItemMeta().hasCustomModelData()))
            return;

        if (this.resultItem.getEnchantmentLevel(Enchantment.SHARPNESS) < 1)
            return;

        System.out.println("1 > " + this.resultItem);

        int enhanceLevel = this.resultItem.getItemMeta().getCustomModelData();
        ItemMeta targetItemMeta = this.resultItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        this.resultItem.setItemMeta(targetItemMeta);

        System.out.println("2 > " + this.resultItem);
        try {
            EnhanceUtil.increaseDmgAndAddLore(this.resultItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("3 > " + this.resultItem);
        event.setResult(this.resultItem);
    }
}
