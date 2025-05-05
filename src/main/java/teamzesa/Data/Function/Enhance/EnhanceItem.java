package teamzesa.Data.Function.Enhance;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * EnhanceItem 을 실질적으로 만드는 부분은 {@link EnhanceItemBuilder}를 참조해주세요.
 *
 * @param enhancePlayer     '강화를 실행한 플레이어'를 가집니다.
 * @param enhanceItem       강화대상 아이템을 가집니다.
 * @param enhanceScroll     강화시 사용된 주문서를 가집니다.
 * @param protectScroll     강화방어용 주문서를 가집니다.
 */
public record EnhanceItem(
        Player enhancePlayer,
        ItemStack enhanceItem,
        ItemStack enhanceScroll,
        ItemStack protectScroll
) {
}
