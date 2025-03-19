package teamzesa.DataBase.entity.Enhance;

import org.bukkit.Material;
import teamzesa.Enum.Enhance.LongRangeWeapon;
import teamzesa.Enum.Enhance.ShortRangeWeapon;

/**
 * 강화 주문서 아이템을 정의합니다.
 * {@link LongRangeWeapon}를 참고해주세요.
 * {@link ShortRangeWeapon}를 참고해주세요.
 */
public interface Weapon extends EnhanceItemInterface {

    /**
     * @param material 아이템을 기입시 해당 해당 아이템 정보를 반환 합니다.
     * @param <E>      {@link Weapon} 타입만 반환합니다.
     * @return         @param 에 기입된 반환 타입입니다.
     * @throws Exception 무기로 허용되지 않은 아이템인 경우입니다.
     */
    static <E extends Weapon> E findByMaterial(Material material) throws Exception {
        return null;
    }

    /**
     * 무기의 재질을 반환합니다.
     *
     * @return {@link Material}
     */
    Material getMaterial();

    /**
     * 무기의 근접 데미지를 반환합니다.
     *
     * @return {@link Double}
     */
    double getShortRangeDamage();
}
