package teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.event.EventRegister.EventRegister;

public class UpdateEnhanceItemPrepareGrindstone implements EventRegister {

    private ItemStack resultItem;

    private final PrepareGrindstoneEvent event;

    public UpdateEnhanceItemPrepareGrindstone(PrepareGrindstoneEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.resultItem = this.event.getResult();
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.resultItem))
            return;

        if (BooleanUtils.isFalse(this.resultItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.resultItem.getItemMeta().hasCustomModelData()))
            return;

        int enhanceLevel = this.resultItem.getItemMeta().getCustomModelData();

        ItemMeta itemMeta = this.resultItem.getItemMeta();
        itemMeta.setCustomModelData(0);
        this.resultItem.setItemMeta(itemMeta);

        try {
            EnhanceUtil.updateEnhanceItemLore(this.resultItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
