package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.EnhanceComment;
import teamzesa.util.Enum.Enhance.ScrollMap;
import teamzesa.util.Enum.Enhance.WeaponMap;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public abstract class EnhanceUtil extends StringComponentExchanger {
    private static final double ENHANCE_BASE_PERCENTAGE = 10;

    public static boolean isMeetsJudgementCriteria(int standardValue) {
        int ranNum = Integer.parseInt(String.valueOf(String.format("%1.0f", Math.random() * 10)));
        return ranNum <= standardValue;
    }

     public static void modifyEnhanceItemModelData(ItemStack enhanceItem, int updateCustomModelData) {
        if (enhanceItem.hasCustomModelData()) {
            enhanceItem.setCustomModelData(enhanceItem.getCustomModelData() + updateCustomModelData);

            List<Component> lore = new ArrayList<>();
            lore.add(getEnhanceStatusComponent(enhanceItem));
            lore.add(getEnhanceDisplayComponent(enhanceItem));

            enhanceItem.lore(lore);
        }
    }

    public static Component getEnhanceStatusComponent(ItemStack enhanceItem) {
        if (enhanceItem.hasCustomModelData())
            for (EnhanceComment enhanceComment : EnhanceComment.values()) {
                if (enhanceItem.getCustomModelData() == enhanceComment.getEnhanceStack())
                    return enhanceComment.getLoreComment();
            }
        return null;
    }

    public static Component getEnhanceDisplayComponent(ItemStack enhanceItem) {
        double damage = getWeaponDamage(enhanceItem);
        double sharpnessDamage = getSharpnessDamage(enhanceItem, damage);
        double totalDamage = getEnhanceState(enhanceItem, sharpnessDamage);
        String comment = String.format("현재 데미지 : %.3f...", totalDamage);

        return Component.text(comment)
                .color(ColorMap.GREEN.getTextColor())
                .decorate(TextDecoration.BOLD);
    }

    public static ScrollMap getScrollType(ItemStack scroll) {
        if (scroll != null) {
            for (ScrollMap scrollMap : ScrollMap.values()) {
                Material scrollMapMaterial = scrollMap.getMaterial();
                if (scrollMapMaterial.equals(scroll.getType()))
                    return scrollMap;
            }
        }
        return null;
    }

    public static ItemStack checkModelData(ItemStack item) {
        if (!item.hasCustomModelData())
            item.setCustomModelData(0);
        return item;
    }

    public static void scrollDiscount(ItemStack scroll, ItemStack protectScroll) {
        if (protectScroll != null)
            protectScroll.setAmount(protectScroll.getAmount() - getProtectScrollDiscount(protectScroll));

        scroll.setAmount(scroll.getAmount() - getScrollDiscount(scroll));
    }

    public static int getScrollDiscount(ItemStack scroll) {
        return getScrollType(scroll).getDiscountValue();
    }

    public static int getProtectScrollDiscount(ItemStack scroll) {
        return getScrollType(scroll).getDiscountProtectValue();
    }

    public static double getWeaponDamage(ItemStack weapon) {
        double damage = 0.0;
        for (WeaponMap weaponInfo : WeaponMap.values()) {
            if (weaponInfo.getMaterial().equals(weapon.getType()))
                damage = weaponInfo.getDamage();
        }
        return damage;
    }

    public static double getSharpnessDamage(ItemStack weapon, double damage) {
        int curItemSharpnessLevel = weapon.getEnchantLevel(Enchantment.DAMAGE_ALL);
        for (int i = 0; i < curItemSharpnessLevel; i++) {
            switch (i) {
                case 0 -> damage += 1;
                case 1, 2, 3, 4 -> damage += 0.5;
            }
        }
        return damage;
    }

    public static double getEnhanceState(ItemStack enhanceItem, double finalItemStatus) {
        if (enhanceItem.hasCustomModelData()) {
            for (int i = 0; i < enhanceItem.getCustomModelData(); i++) {
                double increasePercentage = ENHANCE_BASE_PERCENTAGE + (i * 2);
                finalItemStatus += finalItemStatus * (increasePercentage / 100);
            }
        }
        return finalItemStatus;
    }

    public int getItemCustomModelData(ItemStack item) {
        return checkModelData(item).getCustomModelData();
    }
}
