package teamzesa.r01;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.attackSpeed.EntityNoDamageSet;
import teamzesa.expHandler.PlayerDyingSet;
import teamzesa.expHandler.PlayerExpHealthScale;
import teamzesa.explosive.Explotion;
import teamzesa.totem.RaidAnnouncer;
import teamzesa.totem.TotemStacking;

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
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new EntityNoDamageSet(),this);
        pm.registerEvents(new Explotion(),this);
        pm.registerEvents(new PlayerExpHealthScale(),this);
        pm.registerEvents(new PlayerDyingSet(),this);

        Bukkit.getPluginCommand("totem").setExecutor(new TotemStacking());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        playerExpHealthScale.expChangeEvent(p);
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(40.0);
    }
}
