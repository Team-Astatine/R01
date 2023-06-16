package teamzesa.r01;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.attackSpeed.EntityNoDamageSet;
import teamzesa.expHandler.PlayerDyingSet;
import teamzesa.expHandler.PlayerExpHealthScale;
import teamzesa.explosive.Explotion;
import teamzesa.totem.RaidAnnouncer;
import teamzesa.totem.TotemStacking;

import java.util.Map;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;
    private PlayerExpHealthScale playerExpHealthScale;
    public R01() {
        pm = getServer().getPluginManager();
        playerExpHealthScale = new PlayerExpHealthScale();
    }

    @Override
    public void onEnable() {
        pm.registerEvents(this,this);
        pm.registerEvents(new Explotion(),this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new PlayerDyingSet(),this);
        pm.registerEvents(new EntityNoDamageSet(),this);
        pm.registerEvents(new PlayerExpHealthScale(),this);

        Bukkit.getPluginCommand("totem").setExecutor(new TotemStacking());
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        playerExpHealthScale.expChangeEvent(p);
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }

    @EventHandler
    public void anvil(InventoryClickEvent e) {
        if (!(e.getInventory() instanceof AnvilInventory))
            return;

        HumanEntity humanEntity = e.getViewers().get(0);
        System.out.println(humanEntity.getName());

//        if (anvilInventory.getItem(0) == null && anvilInventory.getItem(1) == null){}
//
//        Map<Enchantment,Integer> leftStuffEnchantOption = anvilInventory.getItem(0).getEnchantments();
//        Map<Enchantment,Integer> rightStuffEnchantOption = anvilInventory.getItem(1).getEnchantments();

        /*for (Enchantment enchantment : leftStuffEnchantOption.keySet()) {
            int level = enchantment.getMaxLevel();
            p.sendMessage(String.valueOf(level));
        }

        for (Enchantment enchantment : rightStuffEnchantOption.keySet()) {
            int level = enchantment.getMaxLevel();
            p.sendMessage(String.valueOf(level));
        }*/

    }
}
