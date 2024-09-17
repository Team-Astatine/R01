package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.exception.Enhance.EnhanceItemMetaException;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.Enhance.*;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

public abstract class EnhanceUtil extends StringComponentExchanger {

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
    public static boolean isMeetsJudgementCriteria(int standardValue) {
        int ranNum = Integer.parseInt(String.format("%1.0f", Math.random() * 10)); //0.0 ~ 1.0
        return ranNum < standardValue;
    }

    /*
    * increaseDamagePercentage
    * 25% × (level + 1)
    */
    public static double getArrowPowerDamage(ItemStack weapon, double baseDmg) {
        double percentage = 0.25 * (weapon.getItemMeta().getEnchantLevel(Enchantment.POWER) + 1);
        return baseDmg * percentage;
    }

    /*
    * increaseDamage
    * 0.5 * sharpnessLevel + 0.5
    */
    public static double getSharpnessDamage(ItemStack weapon) {
        double increaseDmg = 0.5 * weapon.getItemMeta().getEnchantLevel(Enchantment.SHARPNESS) + 0.5;
        return increaseDmg == 0.5 ? 0 : increaseDmg;
    }

    public static void validCustomModelData(ItemStack item, String comment) throws EnhanceItemMetaException {
         if (ObjectUtils.isEmpty(item))
            throw new EnhanceItemMetaException("item == null");

        if (BooleanUtils.isFalse(item.hasItemMeta()))
            throw new EnhanceItemMetaException(comment + " hasItemMeta == null");

        if (BooleanUtils.isFalse(item.getItemMeta().hasCustomModelData()))
            throw new EnhanceItemMetaException(comment + " hasCustomModelData == null");
    }

    public static int validCustomModelData(ItemStack item) {
        if (ObjectUtils.isEmpty(item))
            return 0;

        if (ObjectUtils.isEmpty(item.getItemMeta()))
            return 0;

        if (BooleanUtils.isFalse(item.getItemMeta().hasCustomModelData()))
            return 0;

        return item.getItemMeta().getCustomModelData();
    }

    public static void increaseEnhanceItemLevel(ItemStack item, int updateCount) throws EnhanceItemMetaException {
        List<Component> lore = new ArrayList<>();
        try {
            validCustomModelData(item, "addItemDescription");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(itemMeta.getCustomModelData() + updateCount);

//        최종강화 아이템 설정
        if (itemMeta.getCustomModelData() == 10) {
            itemMeta.setUnbreakable(true);
            itemMeta.setFireResistant(true);
            itemMeta.setRarity(ItemRarity.EPIC);

            ((Damageable) itemMeta).resetDamage();
        }
        item.setItemMeta(itemMeta);

//        정상적인 형태의 강화작업
        if (itemMeta.getCustomModelData() > 0) { // 0 == Remove All Item Lore /enhance 0
            lore.add(getEnhanceStatusLore(item));

            ArmourMap armourMap = ArmourMap.findByItemStack(item);
            if (armourMap.getMaterial().equals(Material.AIR))
                lore.add(getWeaponDamageLore(item));
            else
                lore.add(getEnhanceDecreaseDamagePercentageLore(item));
            item.lore(lore);
        }

//        todo CustomModelData 가 0 일 시 아이템 초기화 작업 로직
    }

    public static Component getEnhanceStatusLore(ItemStack item) {
        try {
            validCustomModelData(item, "getEnhanceStatusLore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return EnhanceStageComment.getEnhancementLoreByLevel(item.getItemMeta().getCustomModelData());
    }

    public static Component getEnhanceDecreaseDamagePercentageLore(ItemStack enhanceItem) {
        try {
            validCustomModelData(enhanceItem, "getEnhanceDecreaseDamagePercentageLore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String comment = String.format("추가 방어율 : %d%%", enhanceItem.getItemMeta().getCustomModelData());

        return Component.text(comment)
                .color(ColorMap.GREEN.getTextColor())
                .decorate(TextDecoration.BOLD);
    }

    public static Component getWeaponDamageLore(ItemStack enhanceItem) {
        //Calculation Origin Dmg + Sharpness Dmg
        double weaponDmg = getShortRangeWeaponCloseDamage(enhanceItem) + getSharpnessDamage(enhanceItem);

        double totalDmg = getCalculatingDamagePercentage(enhanceItem, weaponDmg);
        String comment = String.format("예상 데미지 : %.3f...", totalDmg);

        return Component.text(comment)
                .color(ColorMap.GREEN.getTextColor())
                .decorate(TextDecoration.BOLD);
    }

    public int getItemCustomModelData(ItemStack item) {
        try {
            validCustomModelData(item, "getItemCustomModelData");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initItemCustomModelData(item).getItemMeta().getCustomModelData();
    }

    public static ItemStack initItemCustomModelData(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        if (BooleanUtils.isFalse(itemMeta.hasCustomModelData())) {
            itemMeta.setCustomModelData(0);
            item.setItemMeta(itemMeta);
        }
        return item;
    }

    public static void scrollDiscount(ItemStack scroll, ItemStack protectScroll) {
        try {
            if (ObjectUtils.isNotEmpty(protectScroll)) {
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

    public static double getCalculatingDefencePercentage(ItemStack[] itemStack) {
        int totalPercentage = 0;
        for (ItemStack item : itemStack)
            totalPercentage += validCustomModelData(item);

        return totalPercentage * 0.01;
    }

    private static final double ENHANCE_BASE_PERCENTAGE = 1;
    public static double getCalculatingDamagePercentage(ItemStack itemStack, double totalDamage) {
        try {
            validCustomModelData(itemStack, "getCalculatingDamagePercentage");
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
