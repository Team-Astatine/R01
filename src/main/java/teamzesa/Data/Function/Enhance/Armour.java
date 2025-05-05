package teamzesa.Data.Function.Enhance;

import org.bukkit.Material;
import teamzesa.Enumeration.Enhance.Items.Armour.ArmourList;

/**
 * 방어구 아이템을 강화 아이템을 정의합니다.
 * {@link ArmourList}를 참고해주세요.
 */
public interface Armour extends EnhanceItemInterface {

    /**
     * @param material 아이템의 재질을 기입시 해당 방어구 정보를 반환 합니다.
     * @param <E>      {@link Armour} 타입만 반환합니다.
     * @return         @param 에 기입된 반환 타입입니다.
     * @throws Exception 방어구를 허용되지 않은 방어구인 경우입니다.
     */
    static <E extends Armour> E findByMaterial(Material material) throws Exception {
        return null;
    }

    /**
     * 방어구의 재질을 반환합니다.
     *
     * @return {@link Material}
     */
    Material getMaterial();
}