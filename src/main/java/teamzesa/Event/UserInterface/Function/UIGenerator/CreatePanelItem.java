package teamzesa.Event.UserInterface.Function.UIGenerator;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.Function.StringComponentExchanger;

import java.util.ArrayList;

public class CreatePanelItem extends StringComponentExchanger {

    private ItemStack resultItem;
    private ItemMeta resultItemMeta;
    private Material panelItemMaterial;

    public CreatePanelItem() {}

    public CreatePanelItem setPanelItem(Material panelItemMaterial) {
        this.panelItemMaterial = panelItemMaterial;
        this.resultItem = new ItemStack(this.panelItemMaterial);
        this.resultItemMeta = this.resultItem.getItemMeta();
        return this;
    }

    public CreatePanelItem setPanelItem(ItemStack panelItem) {
        this.panelItemMaterial = panelItem.getType();
        this.resultItem = panelItem;
        this.resultItemMeta = panelItem.getItemMeta();
        return this;
    }

    public CreatePanelItem setDisplayName(String displayName, ColorType color) {
        this.resultItemMeta.displayName(componentExchanger(displayName, color));
        return this;
    }

    public CreatePanelItem setLore(ArrayList<Component> lore) {
        this.resultItemMeta.lore(lore);
        return this;
    }

    public CreatePanelItem isEnchantGlowing(boolean isEnchantGlowing) {
        if (!isEnchantGlowing)
            return this;

        this.resultItemMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        return this;
    }

    public ItemStack createItem() {

        this.resultItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        this.resultItemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        this.resultItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        this.resultItem.setItemMeta(this.resultItemMeta);
        return this.resultItem;
    }
}
