package teamzesa.util.Enum;

import org.bukkit.Material;

public enum ScrollMap {
    SCROLL1(Material.DRAGON_HEAD, 2,2);

    private final Material material;
    private final int discountValue;
    private final int discountProtectValue;

    ScrollMap(Material material, int discountValue, int discountProtectValue) {
        this.discountValue = discountValue;
        this.material = material;
        this.discountProtectValue = discountProtectValue;
    }

    public Material getMaterial() {
        return material;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public int getDiscountProtectValue() {
        return discountProtectValue;
    }
}
