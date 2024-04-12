package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.*;

import java.util.Collections;

public class EnhanceResultStuffGenerator extends StringComponentExchanger {
    /*
    최대 강화 가능 횟수는 10회
    현재 클래스에선 강화에 대한 알고리즘만 처리할 예정
    */

    private final int LOW_LEVEL = 1;
    private final int MAX_LEVEL = 10;
    private final double DAMAGE_INCREASE_PERCENTAGE = 0.5;
    private int currentStuffPercentage;

    private Player weaponsOwner;
    private ItemStack targetStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;

    public EnhanceResultStuffGenerator() {}

    public EnhanceResultStuffGenerator addWeaponOwner(Player player) {
        this.weaponsOwner = player;
        return this;
    }

    public EnhanceResultStuffGenerator addWeaponStuff(ItemStack weaponStuff) {
        this.targetStuff = weaponStuff;
        if (weaponStuff.hasCustomModelData())
            this.currentStuffPercentage = weaponStuff.getCustomModelData();
        else {
            this.targetStuff.setCustomModelData(this.LOW_LEVEL);
            this.currentStuffPercentage = this.targetStuff.getCustomModelData();
        }
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

    public void executeEnhance() {
//        methodImplement
        if (this.currentStuffPercentage > this.MAX_LEVEL) {
            playerSendMsgComponentExchanger(this.weaponsOwner, "이미 최고 레벨입니다.", ColorList.RED);
            return;
        }

        int ranNum = Integer.parseInt(String.valueOf(String.format("%1.0f", Math.random() * 10)));
        boolean isSuccessEnhance = ranNum < getCurrentStuffPercentage();
        if (isSuccessEnhance) successEnhanceScenario();
        else                  failEnhanceScenario();
    }

    private void failEnhanceScenario() {
//            methodImplement
//            debug
}
    
    private void successEnhanceScenario() {
//            methodImplement
        this.targetStuff.lore(null);
        this.targetStuff.lore(Collections.singletonList(getLoreCommentComponent()));
        this.targetStuff.setCustomModelData(++ this.currentStuffPercentage);
    }

    private int getCurrentStuffPercentage() {
        return switch (this.currentStuffPercentage) {
            case 1-> 10;//100%
            case 2-> 9; //90%
            case 3-> 8; //80%
            case 4-> 7; //70%
            case 5-> 6; //60%
            case 6-> 5; //50%
            case 7-> 4; //40%
            case 8-> 3; //30%
            case 9-> 2; //20%
            case 10-> 1;//10%
            default -> 0;
        };
    }

    private @Nullable Component getLoreCommentComponent() {
        return switch (this.targetStuff.getCustomModelData()) {
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
