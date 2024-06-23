package teamzesa.event.Enhance;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import teamzesa.util.Enum.*;

public class EnhanceResultStuffGenerator extends EnhanceUtil {
    private final int LOW_LEVEL = 0;
    private final int MAX_LEVEL = 10;
    private int curItemModelData;

    private Player weaponsOwner;

    private ItemStack enhanceItem;
    private ItemStack enhanceScroll;
    private ItemStack protectScroll;

    private boolean isScrollAmountResult;
    private boolean isProtectScrollAmountResult;

    public EnhanceResultStuffGenerator() {}

    public EnhanceResultStuffGenerator addWeaponOwner(Player player) {
        this.weaponsOwner = player;
        return this;
    }

    public EnhanceResultStuffGenerator addWeaponStuff(ItemStack weaponStuff) {
        this.enhanceItem = checkModelData(weaponStuff);
        return this;
    }

    public EnhanceResultStuffGenerator addScrollStuff(ItemStack scrollStuff) {
        this.enhanceScroll = scrollStuff;
        return this;
    }

    public EnhanceResultStuffGenerator addProtectScrollStuff(ItemStack protectScrollStuff) {
        this.protectScroll = protectScrollStuff;
        return this;
    }

    public void executeEnhance() {
        this.curItemModelData = this.enhanceItem.getItemMeta().getCustomModelData();
        scrollAmountInit();

        if (this.curItemModelData >= this.MAX_LEVEL) {
            playerSendMessage(2, ColorMap.RED);
            return;
        }

        if (!this.isScrollAmountResult) {
            playerSendMessage(8, ColorMap.RED);
            return;
        }

        if (this.protectScroll != null && !this.isProtectScrollAmountResult) {
            playerSendMessage(7, ColorMap.RED);
            return;
        }

        if (isMeetsJudgementCriteria(MAX_LEVEL - this.curItemModelData))
            successEnhanceScenario();
        else {
            boolean isDestructionResult = isMeetsJudgementCriteria(this.curItemModelData);

            if (isDestructionResult && this.protectScroll == null) {
                this.enhanceItem.setAmount(0);
                playerSendMessage(3, ColorMap.RED);
                return;
            }

            if (isDestructionResult)
                playerSendMessage(4, ColorMap.VOTE_COLOR);

            failEnhanceScenario();
        }

        scrollDiscount(this.enhanceScroll, this.protectScroll);
    }

    public void scrollAmountInit() {
        this.isScrollAmountResult = this.enhanceScroll.getAmount() >= getScrollDiscount(this.enhanceScroll);

        this.isProtectScrollAmountResult = false;
        if (this.protectScroll != null)
            this.isProtectScrollAmountResult = this.protectScroll.getAmount() >= getProtectScrollDiscount(this.protectScroll);
    }

    private void successEnhanceScenario() {
        playerSendMessage(6, ColorMap.DISCORD_COLOR);
        modifyEnhanceItemModelData(this.enhanceItem, +1);
    }

    private void failEnhanceScenario() {
        playerSendMessage(5, ColorMap.PINK);
        modifyEnhanceItemModelData(this.enhanceItem, -1);
    }

    private void playerSendMessage(int commentCode, ColorMap commentColor) {
        String comment = switch (commentCode) {
            case 0 -> "무기를 올려주세요.";
            case 1 -> "강화 주문서가 부족합니다.";
            case 2 -> "이미 최고 레벨입니다.";
            case 3 -> "강화에 실패하여 무기가 파괴 되었습니다.";
            case 4 -> "파괴방어 스크롤을 사용하여 파괴방지 성공!";
            case 5 -> " " + this.curItemModelData + "강 -> " + --this.curItemModelData + "강 강화실패";
            case 6 -> " " + this.curItemModelData + "강 -> " + ++this.curItemModelData + "강 강화성공";
            case 7 -> "파괴방지 주문서가 부족하여 강화가 실행되지 않았습니다.";
            case 8 -> "강화 주문서가 부족하여 실행되지 않았습니다.";

            default -> throw new IllegalStateException("UnKnown CommentCode: " + commentCode);
        };

        if (commentCode == 5)
            ++ this.curItemModelData;
        if (commentCode == 6)
            -- this.curItemModelData;

        playerSendMsgComponentExchanger(this.weaponsOwner, comment, commentColor);
    }
}