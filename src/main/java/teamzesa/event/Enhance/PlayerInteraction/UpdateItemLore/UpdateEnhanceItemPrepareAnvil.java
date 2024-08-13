package teamzesa.event.Enhance.PlayerInteraction.UpdateItemLore;

import teamzesa.event.EventRegister.EventRegister;

public class UpdateEnhanceItemPrepareAnvil implements EventRegister {


    public UpdateEnhanceItemPrepareAnvil() {
    }

    @Override
    public void execute() {

    }

    @Override
    public void init() {

//        ItemStack slot0Item = event.getInventory().getItem(0);
//        ItemStack slot1Item = event.getInventory().getItem(1);
//        ItemStack slot2Item = event.getInventory().getItem(2);
//
//        if (slot0Item == null || slot1Item == null)
//            return;
//
//        int slot0Sharpness = slot0Item.getItemMeta().getCustomModelData();
//        ItemMeta item = slot2Item.getItemMeta();
//        item.setCustomModelData(0);
//        slot2Item.setItemMeta(item);
//
//        try {
//            EnhanceUtil.increaseDmgAndAddLore(slot2Item, slot0Sharpness);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        event.setResult(slot2Item);
    }
}
