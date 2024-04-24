package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.*;

import java.util.Collections;

public class EnhanceResultStuffGenerator extends StringComponentExchanger {
    /*
    최대 강화 가능 횟수는 10회
    현재 클래스에선 강화에 대한 알고리즘만 처리할 예정
    */

    private final int LOW_LEVEL = 0;
    private final int MAX_LEVEL = 9;
    private int currentStuffPercentage;

    private Player weaponsOwner;
    private ItemStack enhanceItem;
    private ItemStack scrollStuff;
    private ScrollMap allowedScrollStuff;
    private ItemStack protectScrollStuff;
    private ScrollMap allowedProtectScrollStuff;

    public EnhanceResultStuffGenerator() {}

    public EnhanceResultStuffGenerator addWeaponOwner(Player player) {
        this.weaponsOwner = player;
        return this;
    }

    public EnhanceResultStuffGenerator addWeaponStuff(ItemStack weaponStuff) {
        this.enhanceItem = weaponStuff;

        if (!this.enhanceItem.hasCustomModelData())
            this.enhanceItem.setCustomModelData(this.LOW_LEVEL);
        this.currentStuffPercentage = this.enhanceItem.getCustomModelData();

        return this;
    }

    public EnhanceResultStuffGenerator addScrollStuff(ItemStack scrollStuff) {
        this.scrollStuff = scrollStuff;

         for (ScrollMap scrollMap : ScrollMap.values()) {
             if (scrollMap.getMaterial().equals(scrollStuff.getType()))
                 this.allowedScrollStuff = scrollMap;
         }
        return this;
    }

    public EnhanceResultStuffGenerator addProtectScrollStuff(ItemStack protectScrollStuff) {
        this.protectScrollStuff = protectScrollStuff;

        for (ScrollMap scrollMap : ScrollMap.values()) {
            if (scrollMap.getMaterial().equals(protectScrollStuff.getType()))
                this.allowedProtectScrollStuff = scrollMap;
        }
        return this;
    }

    public void executeEnhance() {
        if (this.currentStuffPercentage > this.MAX_LEVEL) {
            playerSendMessage(2, ColorMap.RED);
            return;
        }

//        Using Scroll
        if (this.protectScrollStuff != null && this.protectScrollStuff.getAmount() > this.allowedProtectScrollStuff.getDiscountProtectValue())
            this.protectScrollStuff.setAmount(
                    this.protectScrollStuff.getAmount() - this.allowedProtectScrollStuff.getDiscountProtectValue());
        this.scrollStuff.setAmount(this.scrollStuff.getAmount() - this.allowedScrollStuff.getDiscountValue());

//        Generator Item
        if (getJudgementPercentage(getCurrentStuffPercentage())) successEnhanceScenario();
        else                                                     failEnhanceScenario();
    }

    private boolean getJudgementPercentage(int standardValue) {
        int ranNum = Integer.parseInt(String.valueOf(String.format("%1.0f", Math.random() * 10)));
        return ranNum < standardValue;
    }

    private void failEnhanceScenario() {
        boolean isDestructionResult = getJudgementPercentage(this.currentStuffPercentage);
        if (isDestructionResult && this.protectScrollStuff == null) {
            this.enhanceItem.setAmount(0);
            playerSendMessage(3, ColorMap.RED);
            return;
        } else playerSendMessage(4, ColorMap.ORANGE);

//        DownGrade
        playerSendMessage(5, ColorMap.PINK);
        this.enhanceItem.setCustomModelData(--this.currentStuffPercentage);
        this.enhanceItem.lore(Collections.singletonList(getLoreCommentComponent()));
    }
    
    private void successEnhanceScenario() {
        playerSendMessage(6, ColorMap.DISCORD_COLOR);
        this.enhanceItem.setCustomModelData(++this.currentStuffPercentage);
        this.enhanceItem.lore(Collections.singletonList(getLoreCommentComponent()));
    }

    private int getCurrentStuffPercentage() {
        return switch (this.currentStuffPercentage) {
            case 0-> 10;//100%
            case 1-> 9; //90%
            case 2-> 8; //80%
            case 3-> 7; //70%
            case 4-> 6; //60%
            case 5-> 5; //50%
            case 6-> 4; //40%
            case 7-> 3; //30%
            case 8-> 2; //20%
            case 9-> 1; //10%
            default -> 0;
        };
    }

    private Component getLoreCommentComponent() {
        for (EnhanceComment enhanceComment : EnhanceComment.values()) {
            if (this.enhanceItem.getCustomModelData() == enhanceComment.getStep())
                return enhanceComment.getLoreComment();
        }
        return null;
    }

    private void playerSendMessage(int commentCode, ColorMap commentColor) {
        String comment = "";
        switch (commentCode) {
            case 0 -> comment = "무기를 올려주세요.";
            case 1 -> comment = "강화 주문서가 부족합니다.";
            case 2 -> comment = "이미 최고 레벨입니다.";
            case 3 -> comment = "강화에 실패하여 무기가 파괴 되었습니다.";
            case 4 -> comment = "파괴방어 스크롤을 사용하여 파괴방지 성공!";
            case 5 -> comment = this.enhanceItem.getDisplayName()
                    +  " " + this.currentStuffPercentage + "강 -> " + --this.currentStuffPercentage + "강 강화실패";
            case 6 -> comment = this.enhanceItem.getDisplayName()
                    +  " " + this.currentStuffPercentage + "강 -> " + ++this.currentStuffPercentage + "강 강화성공";
        }

        if (commentCode == 5) ++this.currentStuffPercentage;
        if (commentCode == 6) --this.currentStuffPercentage;

        playerSendMsgComponentExchanger(this.weaponsOwner, comment, commentColor);
    }
}
