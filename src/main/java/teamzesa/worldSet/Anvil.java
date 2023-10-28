package teamzesa.worldSet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;
import teamzesa.dataValue.EnchantValue;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Anvil implements Listener {

    @EventHandler
    public void onAnvil(@NotNull AnvilUpdateResultEvent e) {

        Optional<ItemStack> leftStuff = Optional.ofNullable(e.getInventory().getItem(0));
        Optional<ItemStack> rightStuff = Optional.ofNullable(e.getInventory().getItem(1));

        Map<Enchantment, Integer> leftStuffEnchants = null;
        if (leftStuff.isPresent())
            leftStuffEnchants = leftStuff.get().getEnchants();

        Map<Enchantment, Integer> rightStuffEnchant = null;
        if (rightStuff.isPresent())
            rightStuffEnchant = rightStuff.get().getEnchants();

        List<EnchantValue> leftOption = new ArrayList<>();
        for (Map.Entry<Enchantment,Integer> entry : leftStuffEnchants.entrySet()) {
            EnchantValue enchantValue = new EnchantValue(entry.getKey(), entry.getValue());
            System.out.println(enchantValue);
            leftOption.add(enchantValue);
        }


        List<EnchantValue> rightOption = new ArrayList<>();
        for (Map.Entry<Enchantment,Integer> entry : rightStuffEnchant.entrySet()) {
            EnchantValue enchantValue = new EnchantValue(entry.getKey(), entry.getValue());
            System.out.println(enchantValue);
            rightOption.add(enchantValue);
        }
    }
}
