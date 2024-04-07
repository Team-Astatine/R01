package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import org.apache.maven.model.InputLocation;
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

    private int currentStuffPercentage;

    private ItemStack weaponStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;

    public EnhanceResultStuffGenerator() {
    }

    public EnhanceResultStuffGenerator(ItemStack weaponStuff, ItemStack scrollStuff, ItemStack protectScrollStuff) {
        addWeaponStuff(weaponStuff);
        addScrollStuff(scrollStuff);
        addProtectScrollStuff(protectScrollStuff);
    }

    public EnhanceResultStuffGenerator addWeaponStuff(ItemStack weaponStuff) {
        this.weaponStuff = weaponStuff;
        if (weaponStuff.hasCustomModelData())
            this.currentStuffPercentage = weaponStuff.getCustomModelData();
        else this.currentStuffPercentage = 0;
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
        int ranNum = Integer.parseInt(String.valueOf(String.format("%1.0f", Math.random() * 10)));
        boolean isSuccessEnhance = ranNum < getCurrentStuffPercentage();
        System.out.println(isSuccessEnhance);
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
        this.weaponStuff.setCustomModelData(this.currentStuffPercentage + 1);

//            lore reload
        this.weaponStuff.lore(null);
        this.weaponStuff.lore(Collections.singletonList(getLoreCommentComponent()));

    }
    private int getCurrentStuffPercentage() {
        return switch (this.currentStuffPercentage) {
            case 0-> 10; //100%
            case 1-> 9; //90%
            case 2-> 8; //80%
            case 3-> 7; //70%
            case 4-> 6; //60%
            case 5-> 5; //50%
            case 6-> 4; //40%
            case 7-> 3; //30%
            case 8-> 2; //20%
            case 9-> 1; //10%
            case 10-> 1;
            default -> 0;
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
