package teamzesa.DataBase.enhance;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.entity.Enhance.EnhanceItem;
import teamzesa.event.Enhance.EnhanceUtil;

public class EnhanceItemBuilder extends EnhanceUtil {

    private Player enhancePlayer;
    private ItemStack enhanceItem;
    private ItemStack enhanceScroll;
    private ItemStack protectScroll;

    public EnhanceItemBuilder() {
    }

    public EnhanceItemBuilder enhancePlayer(Player player) {
        this.enhancePlayer = player;
        return this;
    }

    public EnhanceItemBuilder enhanceItem(ItemStack enhanceItem) {
        this.enhanceItem = initItemCustomModelData(enhanceItem); //CheckUp hasCustomModelData
        return this;
    }

    public EnhanceItemBuilder enhanceScroll(ItemStack enhanceScroll) {
        this.enhanceScroll = enhanceScroll;
        return this;
    }

    public EnhanceItemBuilder protectScroll(ItemStack protectScroll) {
        this.protectScroll = protectScroll;
        return this;
    }

    public EnhanceItem build() {
        return new EnhanceItem(
                this.enhancePlayer,
                this.enhanceItem,
                this.enhanceScroll,
                this.protectScroll
        );
    }
}
