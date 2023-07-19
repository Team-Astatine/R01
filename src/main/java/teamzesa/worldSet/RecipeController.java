package teamzesa.worldSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class RecipeController implements Listener {
    @EventHandler
    public void recipeCheck(CraftItemEvent e) {
        System.out.println("1");

        if (e.getCurrentItem().getType().equals(Material.TNT))
            e.setCancelled(true);

        if (e.getCurrentItem().getType().equals(Material.TNT_MINECART))
            e.setCancelled(true);

    }

    public void registerObsidianPickaxeRecipe(Plugin plugin) {
        // 결과물로 사용할 ItemStack 생성
        ItemStack obsidianPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);

        // ItemStack의 ItemMeta 가져오기
        ItemMeta itemMeta = obsidianPickaxe.getItemMeta();

        // CustomModelData 설정
        itemMeta.setCustomModelData(1);

        // ItemMeta를 ItemStack에 적용
        obsidianPickaxe.setItemMeta(itemMeta);

        // NamespacedKey 생성
        NamespacedKey key = new NamespacedKey(plugin, "obsidian_pickaxe");

        // ShapedRecipe 생성
        ShapedRecipe recipe = new ShapedRecipe(key, obsidianPickaxe);

        // 레시피 패턴 설정
        recipe.shape("DDD", "DOD", " D ");

        // 레시피 재료 설정
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('O', Material.OBSIDIAN);

        // 레시피 등록
        Bukkit.addRecipe(recipe);
    }
}
