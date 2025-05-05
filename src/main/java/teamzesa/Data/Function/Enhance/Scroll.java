package teamzesa.Data.Function.Enhance;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.Enumeration.Enhance.Items.Scroll.ProtectScrollList;
import teamzesa.Enumeration.Enhance.Items.Scroll.ScrollList;

/**
 * 강화 주문서 아이템을 정의합니다.
 * {@link ScrollList}를 참고해주세요.
 * {@link ProtectScrollList}를 참고해주세요.
 */
public interface Scroll extends EnhanceItemInterface {

    /**
     * @param material 아이템을 기입시 해당 해당 아이템 정보를 반환 합니다.
     * @param <E>      {@link Scroll} 타입만 반환합니다.
     * @return         @param 에 기입된 반환 타입입니다.
     * @throws Exception 주문서로 허용되지 않은 아이템인 경우입니다.
     */
    static <E extends Scroll> E findByMaterial(ItemStack material) throws Exception {
        return null;
    }

    /**
     * 주문서의 재질을 반환합니다.
     *
     * @return {@link Material}
     */
    Material getMaterial();

    /**
     * 주문서 사용 시 해당 주문서 소비갯수를 반환합니다.
     *
     * @return {@link Integer}
     */
    int getDiscountValue();
}
