package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Enum.EnhanceComment;
import teamzesa.util.Interface.StringComponentExchanger;

import java.util.ArrayList;

public class Algorithm extends StringComponentExchanger {
    /*
    최대 강화 가능 횟수는 10회
    현재 클래스에선 강화에 대한 알고리즘만 처리할 예정
    */

    private ItemStack weaponStuff;
    private int currentStuffPercentage;

    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;

    public Algorithm() {
    }

    public Algorithm(ItemStack weaponStuff, ItemStack scrollStuff, ItemStack protectScrollStuff) {
        this.weaponStuff = weaponStuff;
        this.scrollStuff = scrollStuff;
        this.protectScrollStuff = protectScrollStuff;

        this.currentStuffPercentage = this.weaponStuff.getCustomModelData();
    }

    public Algorithm addWeaponStuff(ItemStack weaponStuff) {
        this.weaponStuff = weaponStuff;
        this.currentStuffPercentage = weaponStuff.getCustomModelData();
        return this;
    }

    public Algorithm addScrollStuff(ItemStack scrollStuff) {
        this.scrollStuff = scrollStuff;
        return this;
    }

    public Algorithm addProtectScrollStuff(ItemStack protectScrollStuff) {
        this.protectScrollStuff = protectScrollStuff;
        return this;
    }

    public ItemStack executeEnhance() {
//        methodImplement
        return new ItemStack(Material.AIR);
    }

    private void EnhanceCal() {
        boolean isSuccessEnhance = Math.random() * 10 < getCurrentStuffPercentage();

        if (true) {
//            methodImplement
//            debug
            /*
            기존 current stuff custom model data +1,
            이름에 +1로 수정
             */
//            customModelData ++
            this.weaponStuff.setCustomModelData(this.weaponStuff.getCustomModelData() + 1);
            
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(getComponent());
            this.weaponStuff.lore(lore);
        } else {
            /*
            파괴관련 스크롤 확인
             */
        }
    }

    private double getCurrentStuffPercentage() {
        return switch (this.currentStuffPercentage) {
            case 1 -> 9.0; //90%
            case 2 -> 7.0; //70%
            case 3 -> 5.0; //50%
            case 4 -> 3.0; //30%
            case 5 -> 1.0; //10%
            default -> 0.0;
        };
    }

    private Component getComponent() {
        return switch (this.weaponStuff.getCustomModelData()) {
            case 1 -> EnhanceComment.ONE_STEP.getLoreComment();
            case 2 -> EnhanceComment.TWO_STEP.getLoreComment();
            case 3 -> EnhanceComment.THREE_STEP.getLoreComment();
            case 4 -> EnhanceComment.FOUR_STEP.getLoreComment();
            case 5 -> EnhanceComment.FIVE_STEP.getLoreComment();
            default -> null;
        };
    }
}
