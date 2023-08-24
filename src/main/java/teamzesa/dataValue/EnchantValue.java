package teamzesa.dataValue;

import org.bukkit.enchantments.Enchantment;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnchantValue that)) return false;
        return getEnchantLevel() == that.getEnchantLevel() && Objects.equals(getEnchantment(), that.getEnchantment());
    }

    @Override
    public String toString() {
        return "DataValue{" +
                "enchantment=" + enchantment +
                ", enchantLevel=" + enchantLevel +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEnchantment(), getEnchantLevel());
    }
}
