package teamzesa;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.EntityDamageTickingHandler;
import teamzesa.command.NameChanger;
import teamzesa.combat.PlayerDyingHandler;
import teamzesa.combat.PlayerHealthScaleHandler;
import teamzesa.combat.ExplosiveHandler;
import teamzesa.announcer.RaidAnnouncer;
import teamzesa.command.TotemStacking;
import teamzesa.user.UserHandler;

import java.io.File;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;

    public R01() {
        pm = getServer().getPluginManager();
    }

    private void commandHandler() {
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("nameChanger").setExecutor(new NameChanger());
    }

    private void inputUserData() {
        UserHandler userHandler = UserHandler.getInstance();
        File userDataFile = new File(this.getDataFolder(),"userData.yml");

    }

    private void outputUserData() {

    }

    @Override
    public void onEnable() {
        this.commandHandler(); // command set

        pm.registerEvents(this,this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new ExplosiveHandler(),this);
        pm.registerEvents(new PlayerDyingHandler(),this);
        pm.registerEvents(new PlayerHealthScaleHandler(),this);
        pm.registerEvents(new EntityDamageTickingHandler(),this);
    }
}
