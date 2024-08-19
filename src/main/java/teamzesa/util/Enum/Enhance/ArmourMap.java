package teamzesa.util.Enum.Enhance;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import teamzesa.event.Enhance.Interface.Armour;
import teamzesa.event.Enhance.Interface.EnhanceItemCache;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ArmourMap implements Armour, EnhanceItemCache {
    AIR(Material.AIR, 0),

    LEATHER_HELMET(Material.LEATHER_HELMET, 1),
    CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, 1),
    GOLDEN_HELMET(Material.GOLDEN_HELMET, 1),
    IRON_HELMET(Material.IRON_HELMET, 1),
    DIAMOND_HELMET(Material.DIAMOND_HELMET, 1),
    NETHERITE_HELMET(Material.NETHERITE_HELMET, 1),
    TURTLE_HELMET(Material.TURTLE_HELMET, 1),

    LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, 1),
    CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, 1),
    GOLDEN_CHESTPLATE(Material.GOLDEN_CHESTPLATE, 1),
    IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 1),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 1),
    NETHERITE_CHESTPLATE(Material.NETHERITE_CHESTPLATE, 1),

    LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, 1),
    CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, 1),
    GOLDEN_LEGGINGS(Material.GOLDEN_LEGGINGS, 1),
    IRON_LEGGINGS(Material.IRON_LEGGINGS, 1),
    DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 1),
    NETHERITE_LEGGINGS(Material.NETHERITE_LEGGINGS, 1),

    LEATHER_BOOTS(Material.LEATHER_BOOTS, 1),
    CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, 1),
    GOLDEN_BOOTS(Material.GOLDEN_BOOTS, 1),
    IRON_BOOTS(Material.IRON_BOOTS, 1),
    DIAMOND_BOOTS(Material.DIAMOND_BOOTS, 1),
    NETHERITE_BOOTS(Material.NETHERITE_BOOTS, 1);


    private final Material material;
    private final int resistance;
    private final static Map<Material, ArmourMap> CACHED_ITEM = Arrays.stream(values())
            .collect(Collectors.toMap(ArmourMap::getMaterial, Function.identity()));

    ArmourMap(Material material, int resistance) {
        this.material = material;
        this.resistance = resistance;
    }

    public static ArmourMap findByItemStack(ItemStack itemStack) {
        if (BooleanUtils.isFalse(CACHED_ITEM.containsKey(itemStack.getType())))
            return AIR;
        return CACHED_ITEM.get(itemStack.getType());
    }

    public Material getMaterial() {
        return material;
    }

    public int getResistance() {
        return resistance;
    }
}
