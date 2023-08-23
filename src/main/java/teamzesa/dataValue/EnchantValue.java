package teamzesa.dataValue;

import org.bukkit.enchantments.Enchantment;

public class EnchantValue {
    private Enchantment enchantment;
    private int enchantLevel;

    public EnchantValue(Enchantment enchantment, int enchantLevel) {
        this.enchantment = enchantment;
        this.enchantLevel = enchantLevel;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getEnchantLevel() {
        return enchantLevel;
    }

    @Override
    public String toString() {
        return "DataValue{" +
                "enchantment=" + enchantment +
                ", enchantLevel=" + enchantLevel +
                '}';
    }
}
