package teamzesa;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.*;
import teamzesa.command.NameChanger;
import teamzesa.announcer.RaidAnnouncer;
import teamzesa.command.SaveUserData;
import teamzesa.command.TotemStacking;
import teamzesa.user.IOHandler;

import java.io.File;

public final class R01 extends JavaPlugin implements Listener {
    private static PluginManager pm;
    private static IOHandler ioHandler;
    private File userDataFile;

    public R01() {
        pm = getServer().getPluginManager();
        ioHandler = IOHandler.getInstance();
        userDataFile = new File(this.getDataFolder(), "userData.json");
    }

    private void commandHandler() {
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("NameChanger").setExecutor(new NameChanger());
        this.getCommand("SavaUserData").setExecutor(new SaveUserData(userDataFile));
    }

    @Override
    public void onEnable() {
        ioHandler.inputUserData(userDataFile);  // user Data Set
        commandHandler(); // command set

        pm.registerEvents(this,this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new UserJoinHandler(),this);
        pm.registerEvents(new ExplosiveHandler(),this);
        pm.registerEvents(new UserDyingHandler(),this);
        pm.registerEvents(new UserHealthScaleHandler(),this);
        pm.registerEvents(new EntityDamageTickingHandler(),this);
    }

    @Override
    public void onDisable() {
        ioHandler.outputUserData(userDataFile);
    }
}
