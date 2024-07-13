package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.exception.Enhance.EnhanceItemMetaException;
import teamzesa.exception.Enhance.EnhanceScrollTypeException;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.*;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public abstract class EnhanceUtil extends StringComponentExchanger {
    private static final double ENHANCE_BASE_PERCENTAGE = 10;

    public static boolean isMeetsJudgementCriteria(int standardValue) {
        /*
         * 0 -> 1강  100% 0%
         * 1 -> 2강  90%  10%
         * 2 -> 3강  80%  20%
         * 3 -> 4강  70%  30%
         * 4 -> 5강  60%  40%
         * 5 -> 6강  50%  50%
         * 6 -> 7강  40%  60%
         * 7 -> 8강  30%  70%
         * 7 -> 8강  20%  80%
         * 9 -> 10강 10%  90%
         */

        int ranNum = Integer.parseInt(String.format("%1.0f", Math.random() * 10)); //0.0 ~ 1.0
        return ranNum < standardValue;
    }

    public static double getArrowPowerDamage(ItemStack weapon, double baseDmg) {
//        increaseDamagePercentage
//        25% × (level + 1)
        double percentage = 0.25 * (weapon.getItemMeta().getEnchantLevel(Enchantment.POWER) + 1);
        return baseDmg * percentage;
    }

    public static double getSharpnessDamage(ItemStack weapon) {
//        increaseDamage
//        0.5 * sharpnessLevel + 0.5
        double increaseDmg = 0.5 * weapon.getItemMeta().getEnchantLevel(Enchantment.SHARPNESS) + 0.5;
        return increaseDmg == 0.5 ? 0 : increaseDmg;
    }

    public static void isItemHasCustomModelData(ItemStack item,String funtion) throws EnhanceItemMetaException {
        if (item == null)
            throw new EnhanceItemMetaException("item == null");

        if (!item.hasItemMeta())
            throw new EnhanceItemMetaException(funtion + " hasItemMeta == null");

        if (!item.getItemMeta().hasCustomModelData())
            throw new EnhanceItemMetaException(funtion + " hasCustomModelData == null");
    }

    public static void addItemDescription(ItemStack item, int customModelData) throws EnhanceItemMetaException {

        try {
            isItemHasCustomModelData(item, "addItemDescription");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(itemMeta.getCustomModelData() + customModelData);
        item.setItemMeta(itemMeta);

        List<Component> lore = new ArrayList<>();
        lore.add(getEnhanceStatusComponent(item));
        lore.add(getEnhanceDisplayComponent(item));

        item.lore(lore);
    }

    public static Component getEnhanceStatusComponent(ItemStack item) {
        try {
            isItemHasCustomModelData(item, "getEnhanceStatusComponent");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (EnhanceStageComment enhanceStageComment : EnhanceStageComment.values()) {
            if (item.getItemMeta().getCustomModelData() == enhanceStageComment.getEnhanceStack())
                return enhanceStageComment.getLoreComment();
        }

        return Component.text("Unknown Enhancement Status")
                .color(ColorMap.RED.getTextColor())
                .decorate(TextDecoration.ITALIC);
    }

    public static Component getEnhanceDisplayComponent(ItemStack enhanceItem) {
        double weaponDmg = getShortRangeDamage(enhanceItem) + getSharpnessDamage(enhanceItem);
        double totalDmg = calculatingTotalEnhanceStageDamage(enhanceItem, weaponDmg);
        String comment = String.format("현재 데미지 : %.3f...", totalDmg);

        return Component.text(comment)
                .color(ColorMap.GREEN.getTextColor())
                .decorate(TextDecoration.BOLD);
    }

    public int getItemCustomModelData(ItemStack item) {
        try {
            isItemHasCustomModelData(item, "getItemCustomModelData");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkModelData(item).getItemMeta().getCustomModelData();
    }

    public static ItemStack checkModelData(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        if (!itemMeta.hasCustomModelData()) {
            itemMeta.setCustomModelData(0);
            item.setItemMeta(itemMeta);
        }
        return item;
    }

    public static void scrollDiscount(ItemStack scroll, ItemStack protectScroll) {
        try {

            if (protectScroll != null) {
                protectScroll.setAmount(protectScroll.getAmount() - getScrollType(protectScroll).getDiscountProtectValue());
            }

            scroll.setAmount(scroll.getAmount() - getScrollType(scroll).getDiscountValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ScrollMap getScrollType(ItemStack scroll) throws EnhanceScrollTypeException {
        if (scroll == null)
            throw new EnhanceScrollTypeException("getScrollType Parameter Scroll == Null");

        ScrollMap resultScroll = null;
        for (ScrollMap scrollMap : ScrollMap.values()) {
            Material scrollMapMaterial = scrollMap.getMaterial();
            if (scrollMapMaterial.equals(scroll.getType()))
                resultScroll = scrollMap;
        }

        if (resultScroll == null)
            throw new EnhanceScrollTypeException("getScrollType Non Register Scroll > " + scroll);

        return resultScroll;
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

    public static double calculatingTotalEnhanceStageDamage(ItemStack item, double totalDamage) {
        try {
            isItemHasCustomModelData(item, "getEnhanceState");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemMeta itemMeta = item.getItemMeta();
        for (int i = 0; i < itemMeta.getCustomModelData(); i++) {
            double increasePercentage = ENHANCE_BASE_PERCENTAGE + (i * 2);
            totalDamage += totalDamage * (increasePercentage / 100);
        }

        return totalDamage;
    }
}
