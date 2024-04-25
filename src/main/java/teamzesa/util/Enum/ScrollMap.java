package teamzesa.util.Enum;

import org.bukkit.Material;

public enum ScrollMap {
    DRAGON_EGG(Material.DRAGON_EGG,0, 0),
    END_CRYSTAL(Material.END_CRYSTAL, 1,1),
    NETHER_STAR(Material.NETHER_STAR, 1,1),
    TNT(Material.TNT, 1,2),
    CONDUIT(Material.CONDUIT, 1,2),
    DRAGON_HEAD(Material.DRAGON_HEAD, 1,2),
    RABBIT_FOOT(Material.RABBIT_FOOT, 2,3),
    HEART_OF_THE_SEA(Material.HEART_OF_THE_SEA, 3,6),
    TOTEM_OF_UNDYING(Material.TOTEM_OF_UNDYING, 8,16);

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
