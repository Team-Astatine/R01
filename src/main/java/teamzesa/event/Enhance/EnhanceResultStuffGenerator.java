package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Enum.EnhanceComment;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.Collections;
import java.util.Map;

public class EnhanceResultStuffGenerator extends StringComponentExchanger {
    /*
    최대 강화 가능 횟수는 10회
    현재 클래스에선 강화에 대한 알고리즘만 처리할 예정
    */

    private ItemStack weaponStuff;
    private int currentStuffPercentage;

    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;

    public EnhanceResultStuffGenerator() {
    }

    public EnhanceResultStuffGenerator(ItemStack weaponStuff, ItemStack scrollStuff, ItemStack protectScrollStuff) {
        this.weaponStuff = weaponStuff;
        this.scrollStuff = scrollStuff;
        this.protectScrollStuff = protectScrollStuff;

        this.currentStuffPercentage = this.weaponStuff.getCustomModelData();
    }

    public EnhanceResultStuffGenerator addWeaponStuff(ItemStack weaponStuff) {
        this.weaponStuff = weaponStuff;
        this.currentStuffPercentage = weaponStuff.getCustomModelData();
        return this;
    }

    public EnhanceResultStuffGenerator addScrollStuff(ItemStack scrollStuff) {
        this.scrollStuff = scrollStuff;
        return this;
    }

    public EnhanceResultStuffGenerator addProtectScrollStuff(ItemStack protectScrollStuff) {
        this.protectScrollStuff = protectScrollStuff;
        return this;
    }

    public ItemStack executeEnhance() {
//        methodImplement
        boolean isSuccessEnhance = Math.random() * 10 < getCurrentStuffPercentage();
        if (isSuccessEnhance) successEnhanceScenario();
        else                  failEnhanceScenario();

        return new ItemStack(Material.AIR);
    }

    private void failEnhanceScenario() {
//            methodImplement
//            debug
}
    
    private void successEnhanceScenario() {
//            methodImplement
//            debug
            /*
            기존 current stuff custom model data +1,
            이름에 +1로 수정
             */
//            customModelData ++
        this.weaponStuff.setCustomModelData(this.weaponStuff.getCustomModelData() + 1);
//            lore reload
        this.weaponStuff.lore(null);
        this.weaponStuff.lore(Collections.singletonList(getLoreCommentComponent()));

//            weapon Option Set
        Map<Enchantment, Integer> currentStuffEnchant = this.weaponStuff.getEnchantments();

    }
    private double getCurrentStuffPercentage() {
        return switch (this.currentStuffPercentage) {
            case 1-> 9.0; //90%
            case 2-> 8.0; //80%
            case 3-> 7.0; //70%
            case 4-> 6.0; //60%
            case 5-> 5.0; //50%
            case 6-> 4.0; //40%
            case 7-> 3.0; //30%
            case 8-> 2.0; //20%
            case 9-> 1.0; //10%
            default -> 0.0;
        };
    }

    private Component getLoreCommentComponent() {
        return switch (this.weaponStuff.getCustomModelData()) {
            case 1 -> EnhanceComment.ONE_STEP.getLoreComment();
            case 2 -> EnhanceComment.TWO_STEP.getLoreComment();
            case 3 -> EnhanceComment.THREE_STEP.getLoreComment();
            case 4 -> EnhanceComment.FOUR_STEP.getLoreComment();
            case 5 -> EnhanceComment.FIVE_STEP.getLoreComment();
            case 6 -> EnhanceComment.SIX_STEP.getLoreComment();
            case 7 -> EnhanceComment.SEVEN_STEP.getLoreComment();
            case 8 -> EnhanceComment.EIGHT_STEP.getLoreComment();
            case 9 -> EnhanceComment.NINE_STEP.getLoreComment();
            case 10 -> EnhanceComment.TEN_STEP.getLoreComment();
            default -> null;
        };
    }
}
