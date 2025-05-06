package teamzesa.Event.Enhance.Enumeration;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EnhanceItemAttributes {
    AIR                     (Material.AIR, ItemRarity.COMMON, ItemType.AIR, false),

    LEATHER_HELMET          (Material.LEATHER_HELMET, ItemRarity.COMMON, ItemType.ARMOUR, false),
    CHAINMAIL_HELMET        (Material.CHAINMAIL_HELMET, ItemRarity.UNCOMMON, ItemType.ARMOUR, false),
    GOLDEN_HELMET           (Material.GOLDEN_HELMET, ItemRarity.COMMON, ItemType.ARMOUR, false),
    IRON_HELMET             (Material.IRON_HELMET, ItemRarity.COMMON, ItemType.ARMOUR, false),
    DIAMOND_HELMET          (Material.DIAMOND_HELMET, ItemRarity.COMMON, ItemType.ARMOUR, false),
    TURTLE_HELMET           (Material.TURTLE_HELMET, ItemRarity.COMMON, ItemType.ARMOUR, false),
    NETHERITE_HELMET        (Material.NETHERITE_HELMET, ItemRarity.COMMON, ItemType.ARMOUR, true),

    LEATHER_CHESTPLATE      (Material.LEATHER_CHESTPLATE, ItemRarity.COMMON, ItemType.ARMOUR, false),
    CHAINMAIL_CHESTPLATE    (Material.CHAINMAIL_CHESTPLATE, ItemRarity.UNCOMMON, ItemType.ARMOUR, false),
    GOLDEN_CHESTPLATE       (Material.GOLDEN_CHESTPLATE, ItemRarity.COMMON, ItemType.ARMOUR, false),
    IRON_CHESTPLATE         (Material.IRON_CHESTPLATE, ItemRarity.COMMON, ItemType.ARMOUR, false),
    DIAMOND_CHESTPLATE      (Material.DIAMOND_CHESTPLATE, ItemRarity.COMMON, ItemType.ARMOUR, false),
    NETHERITE_CHESTPLATE    (Material.NETHERITE_CHESTPLATE, ItemRarity.COMMON, ItemType.ARMOUR, true),

    LEATHER_LEGGINGS        (Material.LEATHER_LEGGINGS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    CHAINMAIL_LEGGINGS      (Material.CHAINMAIL_LEGGINGS, ItemRarity.UNCOMMON, ItemType.ARMOUR, false),
    GOLDEN_LEGGINGS         (Material.GOLDEN_LEGGINGS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    IRON_LEGGINGS           (Material.IRON_LEGGINGS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    DIAMOND_LEGGINGS        (Material.DIAMOND_LEGGINGS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    NETHERITE_LEGGINGS      (Material.NETHERITE_LEGGINGS, ItemRarity.COMMON, ItemType.ARMOUR, true),

    LEATHER_BOOTS           (Material.LEATHER_BOOTS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    CHAINMAIL_BOOTS         (Material.CHAINMAIL_BOOTS, ItemRarity.UNCOMMON, ItemType.ARMOUR, false),
    GOLDEN_BOOTS            (Material.GOLDEN_BOOTS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    IRON_BOOTS              (Material.IRON_BOOTS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    DIAMOND_BOOTS           (Material.DIAMOND_BOOTS, ItemRarity.COMMON, ItemType.ARMOUR, false),
    NETHERITE_BOOTS         (Material.NETHERITE_BOOTS, ItemRarity.COMMON, ItemType.ARMOUR, true),


    TRIDENT         (Material.TRIDENT, ItemRarity.RARE,   ItemType.WEAPON, false),
    CROSSBOW        (Material.CROSSBOW, ItemRarity.COMMON, ItemType.WEAPON, false),
    BOW             (Material.BOW, ItemRarity.COMMON, ItemType.WEAPON, false),


    WOOD_AXE        (Material.WOODEN_AXE, ItemRarity.COMMON, ItemType.WEAPON, false),
    GOLD_AXE        (Material.GOLDEN_AXE, ItemRarity.COMMON, ItemType.WEAPON, false),
    STONE_AXE       (Material.STONE_AXE, ItemRarity.COMMON, ItemType.WEAPON, false),
    IRON_AXE        (Material.IRON_AXE, ItemRarity.COMMON, ItemType.WEAPON, false),
    DIAMOND_AXE     (Material.DIAMOND_AXE, ItemRarity.COMMON, ItemType.WEAPON, false),
    NETHERITE_AXE   (Material.NETHERITE_AXE, ItemRarity.COMMON, ItemType.WEAPON, true),


    WOOD_SWORD      (Material.WOODEN_SWORD, ItemRarity.COMMON, ItemType.WEAPON, false),
    GOLD_SWORD      (Material.GOLDEN_SWORD, ItemRarity.COMMON, ItemType.WEAPON, false),
    STONE_SWORD     (Material.STONE_SWORD, ItemRarity.COMMON, ItemType.WEAPON, false),
    IRON_SWORD      (Material.IRON_SWORD, ItemRarity.COMMON, ItemType.WEAPON, false),
    DIAMOND_SWORD   (Material.DIAMOND_SWORD, ItemRarity.COMMON, ItemType.WEAPON, false),
    NETHERITE_SWORD (Material.NETHERITE_SWORD, ItemRarity.COMMON, ItemType.WEAPON, true),

    MACE            (Material.MACE, ItemRarity.EPIC,   ItemType.WEAPON, false);

    public enum ItemType {
        ARMOUR,
        WEAPON,
        AIR
    }

    private final Material material;
    private final ItemRarity itemRarity;
    private final ItemType itemType;
    private final boolean isMadeOfNetherite;
    private static final EnumMap<Material, EnhanceItemAttributes> CACHED_ITEM =
            Arrays.stream(values()).collect(
                    Collectors.toMap(
                        EnhanceItemAttributes::getMaterial,
                        Function.identity(),
                        (k1, k2) -> k1,
                        () -> new EnumMap<>(Material.class)
                    )
            );

    EnhanceItemAttributes(Material material, ItemRarity itemRarity, ItemType itemType, boolean isMadeOfNetherite) {
        this.material = material;
        this.itemRarity = itemRarity;
        this.itemType = itemType;
        this.isMadeOfNetherite = isMadeOfNetherite;
    }

    public static EnhanceItemAttributes findByMaterial(ItemStack itemStack) {
        if (BooleanUtils.isFalse((CACHED_ITEM.containsKey(itemStack.getType()))))
            return AIR;

        return CACHED_ITEM.get(itemStack.getType());
    }

    public Material getMaterial() {
        return this.material;
    }

    public ItemRarity getItemRarity() {
        return this.itemRarity;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    public boolean isNetherite() {
        return this.isMadeOfNetherite;
    }
}