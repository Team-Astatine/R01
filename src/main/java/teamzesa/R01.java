package teamzesa;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.*;
import teamzesa.command.*;
import teamzesa.announcer.RaidAnnouncer;
import teamzesa.userValue.UserIOHandler;
import teamzesa.userValue.JoinAndQuit;

import java.io.File;
import java.io.IOException;

public final class R01 extends JavaPlugin {
    private static PluginManager pm;
    private static UserIOHandler userIoHandler;

    public R01() {
        pm = getServer().getPluginManager();
        userIoHandler = UserIOHandler.getIOHandler();
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
        this.getCommand("머리").setExecutor(new ArmourSet());
        this.getCommand("몸통").setExecutor(new ArmourSet());
        this.getCommand("바지").setExecutor(new ArmourSet());
        this.getCommand("신발").setExecutor(new ArmourSet());
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("Motd").setExecutor(new MotdSet());
        this.getCommand("체력초기화").setExecutor(new HealthSet());
        this.getCommand("NameChanger").setExecutor(new NameChanger());
        this.getCommand("SaveUserData").setExecutor(new SaveUserData(checkUpDataFile()));
    }

    @Override
    public void onEnable() {
        userIoHandler.inputUserData(checkUpDataFile());  // user Data Set
        commandHandler(); // command set

        pm.registerEvents(new JoinAndQuit(),this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new Explosive(),this);
        pm.registerEvents(new UserHealthScale(),this);
        pm.registerEvents(new EntityDamageTicking(),this);
    }

    @Override
    public void onDisable() {
        userIoHandler.outputUserData(checkUpDataFile());
    }
}
