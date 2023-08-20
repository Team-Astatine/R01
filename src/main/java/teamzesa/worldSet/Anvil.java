package teamzesa.worldSet;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;

import java.util.EventListener;
import java.util.List;
import java.util.Map;

public class Anvil implements Listener {

    @EventHandler
    public void onAnvil(AnvilUpdateResultEvent e) {

        ItemStack leftStuff = e.getInventory().getItem(0);
        ItemStack rightStuff = e.getInventory().getItem(1);
        ItemStack resultStuff;

        if (leftStuff == null || rightStuff == null)
            return;

        Map<Enchantment, Integer> leftStuffEnchant = leftStuff.getEnchants();
        Map<Enchantment, Integer> rightStuffEnchant = rightStuff.getEnchants();

//        Sharpness
        int leftSharpness = leftStuffEnchant.get(Enchantment.DAMAGE_ALL);
        int rightSharpness = rightStuffEnchant.get(Enchantment.DAMAGE_ALL);

//        Unbreaking
        int leftUnbreaking = leftStuffEnchant.get(Enchantment.DURABILITY);
        int rightUnbreaking = rightStuffEnchant.get(Enchantment.DURABILITY);

//        checkLevel
        if (leftSharpness < 5 && leftSharpness > 10 || leftUnbreaking < 5 && leftUnbreaking > 10)
            return;

        if (leftSharpness != rightSharpness || leftUnbreaking != rightUnbreaking)
            return;

        resultStuff = rightStuff.clone();
        for (Map.Entry<Enchantment,Integer> entry : leftStuffEnchant.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int enchantLevel = entry.getValue();

            if (enchantment == Enchantment.DAMAGE_ALL) {
                resultStuff.addEnchant(Enchantment.DAMAGE_ALL, enchantLevel + 1, true);
            } else if (enchantment == Enchantment.DURABILITY) {
                resultStuff.addEnchant(Enchantment.DURABILITY, enchantLevel + 1, true);
            } else {
                resultStuff.addEnchant(enchantment, enchantLevel, true);
            }
        }
        System.out.println(resultStuff);
    }
}
