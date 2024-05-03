package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.*;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public abstract class EnhanceUtil extends StringComponentExchanger {
    private static final double ENHANCE_BASE_PERCENTAGE = 10;

    public static boolean isMeetsJudgementCriteria(int standardValue) {
        int ranNum = Integer.parseInt(String.valueOf(String.format("%1.0f", Math.random() * 10)));
        return ranNum <= standardValue;
    }

    public static double getArrowPowerDamage(ItemStack weapon, double baseDmg) {
//        increaseDamagePercentage
//        25% × (level + 1)
        double percentage = 0.25 * (weapon.getEnchantLevel(Enchantment.ARROW_DAMAGE) + 1);
        return baseDmg * percentage;
    }

    public static double getSharpnessDamage(ItemStack weapon) {
//        increaseDamage
//        0.5 * sharpnessLevel + 0.5
        return 0.5 * weapon.getEnchantLevel(Enchantment.DAMAGE_ALL) + 0.5;
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
        double weaponDmg = getShortRangeDamage(enhanceItem) + getSharpnessDamage(enhanceItem);
        double totalDmg = getEnhanceState(enhanceItem, weaponDmg);
        String comment = String.format("현재 데미지 : %.3f...", totalDmg);

        return Component.text(comment)
                .color(ColorMap.GREEN.getTextColor())
                .decorate(TextDecoration.BOLD);
    }

    public int getItemCustomModelData(ItemStack item) {
        return checkModelData(item).getCustomModelData();
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

    public static double getShortRangeDamage(ItemStack weapon) {
        double damage = 0.0;
        for (Weapon weaponInfo : ShortRangeWeaponMap.values()) {
            if (weaponInfo.getMaterial().equals(weapon.getType()))
                damage = weaponInfo.getShortRangeDamage();
        }

        for (Weapon weaponInfo : LongRangeWeaponMap.values()) {
            if (weaponInfo.getMaterial().equals(weapon.getType()))
                damage = weaponInfo.getShortRangeDamage();
        }

        return damage;
    }

    public static double getLongRangeDamage(ItemStack weapon) {
        double damage = 0.0;
        for (Weapon weaponInfo : LongRangeWeaponMap.values()) {
            if (weaponInfo.getMaterial().equals(weapon.getType()))
                damage = weaponInfo.getLongRangeDamage();
        }
        return damage;
    }

    public static double getEnhanceState(ItemStack enhanceItem, double itemFinalDamage) {
        if (enhanceItem.hasCustomModelData()) {
            for (int i = 0; i < enhanceItem.getCustomModelData(); i++) {
                double increasePercentage = ENHANCE_BASE_PERCENTAGE + (i * 2);
                itemFinalDamage += itemFinalDamage * (increasePercentage / 100);
            }
        }
        return itemFinalDamage;
    }
}
