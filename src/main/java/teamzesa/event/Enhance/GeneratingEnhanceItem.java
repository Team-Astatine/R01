package teamzesa.event.Enhance;

import org.bukkit.Material;
import teamzesa.DataBase.entity.EnhanceItem;
import teamzesa.util.Enum.*;

public class GeneratingEnhanceItem extends EnhanceUtil {
    private final int LOW_LEVEL = 0;
    private final int MAX_LEVEL = 10;

    private final EnhanceItem item;

    private boolean hasProtectScroll;

    private boolean isScrollEnough;
    private boolean isProtectScrollEnough;

    public GeneratingEnhanceItem(EnhanceItem item) {
        this.item = item;

        init();
        executeEnhance();
    }

    private void init() {
        this.hasProtectScroll = false;
        this.isScrollEnough = false;
        this.isProtectScrollEnough = false;

//        existProtectScroll
        if (this.item.protectScroll() != null)
            this.hasProtectScroll = true;

//        enhance
        this.isScrollEnough = this.item.enhanceScroll().getAmount() >= getScrollDiscount(this.item.enhanceScroll());

//        protect
        if (hasProtectScroll)
            this.isProtectScrollEnough =
                    this.item.protectScroll().getAmount() >= getProtectScrollDiscount(this.item.protectScroll());
    }

    private void executeEnhance() {
//        MaxLvlCheck
        int currentCustomModelData = this.item.enhanceItem().getItemMeta().getCustomModelData();

        if (currentCustomModelData >= this.MAX_LEVEL) {
            playerSendMessage(2, ColorMap.RED);
            return;
        }

//        Scroll Valid
        if (!this.isScrollEnough) {
            playerSendMessage(8, ColorMap.RED);
            return;
        }
        if (this.hasProtectScroll && !this.isProtectScrollEnough) {
            playerSendMessage(7, ColorMap.RED);
            return;
        }

        boolean isSuccess = isMeetsJudgementCriteria(MAX_LEVEL - currentCustomModelData);
        boolean isDestroy = isMeetsJudgementCriteria(currentCustomModelData - LOW_LEVEL);

//        execute
        if (isSuccess) {
            successEnhanceScenario();
            scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
            return;
        }

        if (!isDestroy) {
            failEnhanceScenario();
            scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
            return;
        }

        if (!this.hasProtectScroll) {
            this.item.enhanceItem().setAmount(0);
            playerSendMessage(3, ColorMap.RED);
            scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
            return;
        }

        playerSendMessage(4, ColorMap.VOTE_COLOR);
        failEnhanceScenario();
        scrollDiscount(this.item.enhanceScroll(), this.item.protectScroll());
    }

    private void successEnhanceScenario() {
        playerSendMessage(6, ColorMap.DISCORD_COLOR);
        modifyEnhanceItemModelData(this.item.enhanceItem(), +1);
    }

    private void failEnhanceScenario() {
        playerSendMessage(5, ColorMap.PINK);
        modifyEnhanceItemModelData(this.item.enhanceItem(), -1);
    }

    private void playerSendMessage(int commentCode, ColorMap commentColor) {
        int currentCustomModelData = 0;
        if (this.item.enhanceItem().getType() != Material.AIR)
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