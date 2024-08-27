package teamzesa.event.Enhance.Interface;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.exception.Enhance.EnhanceItemMetaException;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.*;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public abstract class EnhanceUtil extends StringComponentExchanger {
    private static final double ENHANCE_BASE_PERCENTAGE = 1;

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

    public static void isItemHasCustomModelData(ItemStack item, String funtion) throws EnhanceItemMetaException {
         if (ObjectUtils.isEmpty(item))
            throw new EnhanceItemMetaException("item == null");

        if (BooleanUtils.isFalse(item.hasItemMeta()))
            throw new EnhanceItemMetaException(funtion + " hasItemMeta == null");

        if (BooleanUtils.isFalse(item.getItemMeta().hasCustomModelData()))
            throw new EnhanceItemMetaException(funtion + " hasCustomModelData == null");
    }

    public static void updateEnhanceItemLore(ItemStack item, int updateCount) throws EnhanceItemMetaException {
        List<Component> lore = new ArrayList<>();
        try {
            isItemHasCustomModelData(item, "addItemDescription");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setCustomModelData(itemMeta.getCustomModelData() + updateCount);
        if (itemMeta.getCustomModelData() == 10)
            itemMeta.setUnbreakable(true);

        item.setItemMeta(itemMeta);

        if (itemMeta.getCustomModelData() > 0) { // 0 == Remove All Item Lore /enhance 0
            lore.add(getEnhanceStatusComponent(item));

            ArmourMap armourMap = ArmourMap.findByItemStack(item);
            if (armourMap.getMaterial().equals(Material.AIR))
                lore.add(getEnhanceWeaoonDisplayComponent(item));
            else
                lore.add(getEnhanceAmourDisplayComponent(item));
        }

        item.lore(lore);
    }

    public static Component getEnhanceStatusComponent(ItemStack item) {
        try {
            isItemHasCustomModelData(item, "getEnhanceStatusComponent");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return EnhanceStageComment.findByEnhanceLevelComment(item.getItemMeta().getCustomModelData());
    }

    public static Component getEnhanceAmourDisplayComponent(ItemStack enhanceItem) {
        try {
            isItemHasCustomModelData(enhanceItem, "getEnhanceStatusComponent");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String comment = String.format("추가 방어율 : %d%%", enhanceItem.getItemMeta().getCustomModelData());

        return Component.text(comment)
                .color(ColorMap.GREEN.getTextColor())
                .decorate(TextDecoration.BOLD);
    }

    public static Component getEnhanceWeaoonDisplayComponent(ItemStack enhanceItem) {
        //Calculation Origin Dmg + Sharpness Dmg
        double weaponDmg = getShortRangeWeaponCloseDamage(enhanceItem) + getSharpnessDamage(enhanceItem);

        double totalDmg = calculatingTotalEnhanceStageDamage(enhanceItem, weaponDmg);
        String comment = String.format("예상 데미지 : %.3f...", totalDmg);

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
        if (BooleanUtils.isFalse(itemMeta.hasCustomModelData())) {
            itemMeta.setCustomModelData(0);
            item.setItemMeta(itemMeta);
        }
        return item;
    }

    public static void scrollDiscount(ItemStack scroll, ItemStack protectScroll) {
        try {
            if (ObjectUtils.notEqual(protectScroll, null)) {
                protectScroll.setAmount(protectScroll.getAmount()
                        - ProtectScroll.findByItemStack(protectScroll).getDiscountValue());
            }

            scroll.setAmount(scroll.getAmount() - Scroll.findByItemStack(scroll).getDiscountValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getShortRangeWeaponCloseDamage(ItemStack weapon) {
        double damage = 0.0;
        ShortRangeWeaponMap shortRangeWeaponMap = ShortRangeWeaponMap.findByItemStack(weapon);
        LongRangeWeaponMap longRangeWeaponMap = LongRangeWeaponMap.findByItemStack(weapon);

        if (ObjectUtils.notEqual(shortRangeWeaponMap, ShortRangeWeaponMap.AIR))
            return shortRangeWeaponMap.getShortRangeDamage();

        if (ObjectUtils.notEqual(longRangeWeaponMap, LongRangeWeaponMap.AIR))
            return longRangeWeaponMap.getShortRangeDamage();

        return damage;
    }

    public static double calculatingTotalResistancePercentage(ItemStack[] itemStack, double totalDamage) {
        int totalPercentage = 0;
        for (ItemStack item : itemStack)
            totalPercentage += getCustomModelData(item);

        return totalDamage - (totalDamage * totalPercentage * 0.01);
    }

    public static int getCustomModelData(ItemStack item) {
        if (ObjectUtils.isEmpty(item))
            return 0;

        if (ObjectUtils.isEmpty(item.getItemMeta()))
            return 0;

        if (BooleanUtils.isFalse(item.getItemMeta().hasCustomModelData()))
            return 0;

        return item.getItemMeta().getCustomModelData();
    }

    public static double calculatingTotalEnhanceStageDamage(ItemStack itemStack, double totalDamage) {
        try {
            isItemHasCustomModelData(itemStack, "calculatingTotalEnhanceStageDamage");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        for (int i = 0; i < itemMeta.getCustomModelData(); i++) {
            double increasePercentage = ENHANCE_BASE_PERCENTAGE + i;
            totalDamage += totalDamage * (increasePercentage / 100);
        }

        return totalDamage;
    }
}
