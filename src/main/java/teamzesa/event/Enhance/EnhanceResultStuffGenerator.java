package teamzesa.event.Enhance;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
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
    private final int MAX_LEVEL = 10;
    private int currentStuffPercentage;

    private Player weaponsOwner;

    private ItemStack enhanceItem;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;

    private ScrollMap scrollInfo;
    private ScrollMap protectScrollInfo;

    private boolean isScrollAmountResult;
    private boolean isProtectScrollAmountResult;

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
        return this;
    }

    public EnhanceResultStuffGenerator addProtectScrollStuff(ItemStack protectScrollStuff) {
        this.protectScrollStuff = protectScrollStuff;
        return this;
    }

    public void executeEnhance() {
        init();

        if (this.currentStuffPercentage >= this.MAX_LEVEL) {
            playerSendMessage(2, ColorMap.RED);
            return;
        }

        if (!this.isScrollAmountResult) {
            playerSendMessage(8, ColorMap.RED);
            return;
        }

        if (this.protectScrollStuff != null && !this.isProtectScrollAmountResult) {
            playerSendMessage(7, ColorMap.RED);
            return;
        }

        if (getJudgementPercentage(MAX_LEVEL - this.currentStuffPercentage))
            successEnhanceScenario();
        else {
            boolean isDestructionResult = getJudgementPercentage(this.currentStuffPercentage);

            if (isDestructionResult && this.protectScrollStuff == null) {
                this.enhanceItem.setAmount(0);
                playerSendMessage(3, ColorMap.RED);
            }
            if (isDestructionResult && this.protectScrollStuff != null)
                playerSendMessage(4, ColorMap.ORANGE);

            failEnhanceScenario();
        }
        decreaseScrollAmount();
    }

    private void init() {
        this.scrollInfo = getScrollType(this.scrollStuff);
        this.protectScrollInfo = getScrollType(this.protectScrollStuff);

        this.isScrollAmountResult = this.scrollStuff.getAmount() >= this.scrollInfo.getDiscountProtectValue();
        this.isProtectScrollAmountResult = false;
        if (this.protectScrollStuff != null && this.protectScrollInfo != null)
            this.isProtectScrollAmountResult = this.protectScrollStuff.getAmount() >= this.protectScrollInfo.getDiscountProtectValue();
    }

    private boolean getJudgementPercentage(int standardValue) {
        int ranNum = Integer.parseInt(String.valueOf(String.format("%1.0f", Math.random() * 10)));
        return ranNum < standardValue;
    }

    private void failEnhanceScenario() {
        playerSendMessage(5, ColorMap.PINK);
        this.enhanceItem.setCustomModelData(--this.currentStuffPercentage);
        this.enhanceItem.lore(Collections.singletonList(getLoreCommentComponent()));
    }

    private void successEnhanceScenario() {
        playerSendMessage(6, ColorMap.DISCORD_COLOR);
        this.enhanceItem.setCustomModelData(++this.currentStuffPercentage);
        this.enhanceItem.lore(Collections.singletonList(getLoreCommentComponent()));
    }

    private void decreaseScrollAmount() {
        if (this.protectScrollStuff != null)
            this.protectScrollStuff.setAmount(this.protectScrollStuff.getAmount() - this.protectScrollInfo.getDiscountProtectValue());
        this.scrollStuff.setAmount(this.scrollStuff.getAmount() - this.scrollInfo.getDiscountProtectValue());
    }

    private Component getLoreCommentComponent() {
        for (EnhanceComment enhanceComment : EnhanceComment.values()) {
            if (this.enhanceItem.getCustomModelData() == enhanceComment.getStep())
                return enhanceComment.getLoreComment();
        }
        return null;
    }

    private ScrollMap getScrollType(ItemStack targetItem) {
        if (targetItem == null)
            return null;

        for (ScrollMap scrollMap : ScrollMap.values()) {
            Material scrollMapMaterial = scrollMap.getMaterial();
            if (scrollMapMaterial.equals(targetItem.getType()))
                return scrollMap;
        }
        return null;
    }

    private void playerSendMessage(int commentCode, ColorMap commentColor) {
        String comment = switch (commentCode) {
            case 0 -> "무기를 올려주세요.";
            case 1 -> "강화 주문서가 부족합니다.";
            case 2 -> "이미 최고 레벨입니다.";
            case 3 -> "강화에 실패하여 무기가 파괴 되었습니다.";
            case 4 -> "파괴방어 스크롤을 사용하여 파괴방지 성공!";
            case 5 -> this.enhanceItem.getDisplayName()
                    + " " + this.currentStuffPercentage + "강 -> " + --this.currentStuffPercentage + "강 강화실패";
            case 6 -> this.enhanceItem.getDisplayName()
                    + " " + this.currentStuffPercentage + "강 -> " + ++this.currentStuffPercentage + "강 강화성공";
            case 7 -> "파괴방지 주문서가 부족하여 강화가 실행되지 않았습니다.";
            case 8 -> "강화 주문서가 부족하여 실행되지 않았습니다.";

            default -> throw new IllegalStateException("Unexpected value: " + commentCode);
        };

        if (commentCode == 5)
            ++ this.currentStuffPercentage;
        if (commentCode == 6)
            -- this.currentStuffPercentage;

        playerSendMsgComponentExchanger(this.weaponsOwner, comment, commentColor);
    }
}