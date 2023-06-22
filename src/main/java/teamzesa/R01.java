package teamzesa;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.comabt.EntityNoDamageSet;
import teamzesa.commandHandler.NameChanger;
import teamzesa.comabt.PlayerDyingSet;
import teamzesa.comabt.PlayerExpHealthScale;
import teamzesa.comabt.Explotion;
import teamzesa.announcer.RaidAnnouncer;
import teamzesa.commandHandler.TotemStacking;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;

    public R01() {
        pm = getServer().getPluginManager();
    }

    private void commandHandler() {
        this.getCommand("nameChanger").setExecutor(new NameChanger());
        this.getCommand("토템").setExecutor(new TotemStacking());
    }

    @Override
    public void onEnable() {
        this.commandHandler(); // command set

        pm.registerEvents(this,this);
        pm.registerEvents(new Explotion(),this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new PlayerDyingSet(),this);
        pm.registerEvents(new EntityNoDamageSet(),this);
        pm.registerEvents(new PlayerExpHealthScale(),this);
    }
}
