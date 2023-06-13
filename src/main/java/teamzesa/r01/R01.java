package teamzesa.r01;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.totem.RaidAnnouncer;
import teamzesa.totem.TotemStacking;

import java.util.Objects;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;

    public R01() {
        pm = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {
        pm.registerEvents(new RaidAnnouncer(),this);

        Bukkit.getPluginCommand("totem").setExecutor(new TotemStacking());
    }
}
