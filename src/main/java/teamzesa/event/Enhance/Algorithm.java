package teamzesa.event.Enhance;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Algorithm {
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

        return new ItemStack(Material.AIR);
    }

    private void EnhanceCal() {
        double currentStuffPercentage = 0.0;
        switch (this.currentStuffPercentage) {
            case 1 -> currentStuffPercentage = 9.0; //90%
            case 2 -> currentStuffPercentage = 7.0; //70%
            case 3 -> currentStuffPercentage = 5.0; //50%
            case 4 -> currentStuffPercentage = 3.0; //30%
            case 5 -> currentStuffPercentage = 1.0; //10%
        }

        boolean isSuccessEnhance = Math.random() * 10 < currentStuffPercentage;
        if (isSuccessEnhance) {
            /*
            기존 current stuff custom model data +1,
            이름에 +1로 수정
             */
        } else {
            /*
            파괴관련 스크롤 확인
             */
        }
    }
}
