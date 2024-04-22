package teamzesa.util.Enum;

import org.bukkit.Material;

public enum WeaponMap {
//    axe
    WOOD_AXE(Material.WOODEN_AXE, 7.0),
    GOLD_AXE(Material.GOLDEN_AXE, 7.0),
    STONE_AXE(Material.STONE_AXE, 9.0),
    IRON_AXE(Material.IRON_AXE, 9.0),
    DIAMOND_AXE(Material.DIAMOND_AXE, 9.0),
    NETHER_AXE(Material.NETHERITE_AXE, 10.0),

//    sword
    WOOD_SWORD(Material.WOODEN_SWORD, 4.0),
    GOLD_SWORD(Material.GOLDEN_SWORD, 4.0),
    STONE_SWORD(Material.STONE_SWORD, 5.0),
    IRON_SWORD(Material.IRON_SWORD, 6.0),
    DIAMOND_SWORD(Material.DIAMOND_SWORD, 7.0),
    NETHER_SWORD(Material.NETHERITE_SWORD, 8.0),

//    trident
    TRIDENT(Material.TRIDENT, 9.0);

    private final Material material;
    private final double damage;

    WeaponMap(Material material, double damage) {
        this.material = material;
        this.damage = damage;
    }

    public Material getMaterial() {
        return material;
    }

    public double getDamage() {
        return damage;
    }
}
