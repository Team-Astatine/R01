package teamzesa.event.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import teamzesa.DataBase.entity.Enhance.EnhanceItem;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.Enhance.ProtectScrollList;
import teamzesa.Enum.Enhance.ScrollList;
import teamzesa.util.Interface.StringComponentExchanger;

public class GeneratingEnhanceItem extends StringComponentExchanger {
    private final int LOW_LEVEL = 0;
    private final int MAX_LEVEL = 10;

    private final EnhanceItem item;

    private boolean hasProtectScroll;

    private boolean scrollEnough;
    private boolean protectScrollEnough;

    public GeneratingEnhanceItem(EnhanceItem item) {
        this.item = item;

        init();
        executeEnhance();
    }

    private void init() {
        this.hasProtectScroll = false;
        this.scrollEnough = false;
        this.protectScrollEnough = false;

//        existProtectScroll
        if (ObjectUtils.allNotNull(this.item.protectScroll()))
            this.hasProtectScroll = true;

        try {// enhance
            this.scrollEnough =
                    this.item.enhanceScroll().getAmount() >= ScrollList.findByItemStack(this.item.enhanceScroll()).getDiscountValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { // protect
            if (hasProtectScroll) {
                this.protectScrollEnough =
                        this.item.protectScroll().getAmount() >= ProtectScrollList.findByItemStack(this.item.protectScroll()).getDiscountValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeEnhance() {
//        MaxLvlCheck
        int currentCustomModelData = this.item.enhanceItem().getItemMeta().getCustomModelData();

        if (currentCustomModelData >= this.MAX_LEVEL) {
            playerSendMessage(2, ColorList.RED);
            return;
        }

//        Scroll Valid
        if (BooleanUtils.isFalse(this.scrollEnough)) {
            playerSendMessage(8, ColorList.RED);
            return;
        }
        if (this.hasProtectScroll && BooleanUtils.isFalse(this.protectScrollEnough)) {
            playerSendMessage(7, ColorList.RED);
            return;
        }

        boolean success = EnhanceUtil.isMeetsJudgementCriteria(MAX_LEVEL - currentCustomModelData);
        boolean destroy = EnhanceUtil.isMeetsJudgementCriteria(currentCustomModelData - LOW_LEVEL);

//        execute
        if (success) {
            successEnhanceScenario();
            EnhanceUtil.scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
            return;
        }

        if (BooleanUtils.isFalse(destroy)) {
            failEnhanceScenario();
            EnhanceUtil.scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
            return;
        }

        if (destroy && BooleanUtils.isFalse(this.hasProtectScroll)) {
            this.item.enhanceItem().setAmount(0);
            playerSendMessage(3, ColorList.RED);
            EnhanceUtil.scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
            return;
        }

        failEnhanceScenario();
        playerSendMessage(4, ColorList.VOTE_COLOR);
        EnhanceUtil.scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
    }

    private void successEnhanceScenario() {
        playerSendMessage(6, ColorList.DISCORD_COLOR);
        try {
            EnhanceUtil.increaseEnhanceItemLevel(this.item.enhanceItem(), +1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void failEnhanceScenario() {
        playerSendMessage(5, ColorList.PINK);
        try {
            EnhanceUtil.increaseEnhanceItemLevel(this.item.enhanceItem(), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playerSendMessage(int commentCode, ColorList commentColor) {
        int currentCustomModelData = 0;
        if (ObjectUtils.notEqual(this.item.enhanceItem().getType(), Material.AIR))
            currentCustomModelData = this.item.enhanceItem().getItemMeta().getCustomModelData();

        String comment = switch (commentCode) {
            case 0 -> "무기를 올려주세요.";
            case 1 -> "강화 주문서가 부족합니다.";
            case 2 -> "이미 최고 레벨입니다.";
            case 3 -> "강화에 실패하여 무기가 파괴 되었습니다.";
            case 4 -> "파괴방어 스크롤을 사용하여 파괴방지 성공!";
            case 5 -> " " + currentCustomModelData + "강 -> " + --currentCustomModelData + "강 강화실패";
            case 6 -> " " + currentCustomModelData + "강 -> " + ++currentCustomModelData + "강 강화성공";
            case 7 -> "파괴방지 주문서가 부족하여 강화가 실행되지 않았습니다.";
            case 8 -> "강화 주문서가 부족하여 실행되지 않았습니다.";

            default -> throw new IllegalStateException("UnKnown CommentCode: " + commentCode);
        };

        playerSendMsgComponentExchanger(this.item.enhancePlayer(), comment, commentColor);
    }
}