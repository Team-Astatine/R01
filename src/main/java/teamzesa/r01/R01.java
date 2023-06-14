package teamzesa.r01;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.attackSpeed.EntityNoDamageSet;
import teamzesa.attackSpeed.PlayerAttackSpeed;
import teamzesa.expHandler.PlayerExpHealthScale;
import teamzesa.totem.RaidAnnouncer;
import teamzesa.totem.TotemStacking;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;

    public R01() {
        pm = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {
//        pm.registerEvents(PlayerAttackSpeed.getInstance(),this);
        pm.registerEvents(new PlayerAttackSpeed(),this);
        pm.registerEvents(new PlayerExpHealthScale(),this);
        pm.registerEvents(new EntityNoDamageSet(),this);
        pm.registerEvents(new RaidAnnouncer(),this);

        Bukkit.getPluginCommand("totem").setExecutor(new TotemStacking());
    }
}
