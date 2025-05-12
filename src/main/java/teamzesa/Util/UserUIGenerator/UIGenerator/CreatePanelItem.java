package teamzesa.Util.UserUIGenerator.UIGenerator;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.Function.StringComponentExchanger;

public class CreatePanelItem extends StringComponentExchanger {

    private PanelItem panelItem;
    private String comment;
    private ColorType color;

    public CreatePanelItem() {}

    public CreatePanelItem setPanelItem(PanelItem panelItem) {
        this.panelItem = panelItem;
        return this;
    }

    public CreatePanelItem setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CreatePanelItem setColor(ColorType color) {
        this.color = color;
        return this;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(this.panelItem.items());
        ItemMeta meta = item.getItemMeta();

        meta.displayName(componentExchanger(this.comment, this.color));

        meta.addEnchant(Enchantment.CHANNELING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setCustomModelData(this.panelItem.customModelData());
        item.setItemMeta(meta);
        return item;
    }
}
