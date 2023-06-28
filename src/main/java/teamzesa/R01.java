package teamzesa;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.*;
import teamzesa.command.NameChanger;
import teamzesa.announcer.RaidAnnouncer;
import teamzesa.command.SaveUserData;
import teamzesa.command.TotemStacking;
import teamzesa.userValue.IOHandler;

import java.io.File;
import java.io.IOException;

public final class R01 extends JavaPlugin {
    private static PluginManager pm;
    private static IOHandler ioHandler;

    public R01() {
        pm = getServer().getPluginManager();
        ioHandler = IOHandler.getIOInstance();
    }

    private File checkUpDataFile() {
        File file = new File(this.getDataFolder(), "userData.json");

        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return file;
    }

    private void commandHandler() {
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("NameChanger").setExecutor(new NameChanger());
        this.getCommand("SaveUserData").setExecutor(new SaveUserData(checkUpDataFile()));
    }

    @Override
    public void onEnable() {
        ioHandler.inputUserData(checkUpDataFile());  // user Data Set
        commandHandler(); // command set

        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new UserJoinHandler(),this);
        pm.registerEvents(new ExplosiveHandler(),this);
        pm.registerEvents(new UserDyingHandler(),this);
        pm.registerEvents(new UserHealthScaleHandler(),this);
        pm.registerEvents(new EntityDamageTickingHandler(),this);
    }

    @Override
    public void onDisable() {
        ioHandler.outputUserData(checkUpDataFile());
    }
}
