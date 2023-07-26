package teamzesa;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.IOHandler.IOHandler;
import teamzesa.combat.*;
import teamzesa.command.*;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.IOHandler.DataFile;
import teamzesa.userValue.Death;
import teamzesa.worldSet.RaidAnnouncer;
import teamzesa.IOHandler.UserIOHandler;
import teamzesa.userValue.JoinAndQuit;
import teamzesa.worldSet.RecipeController;

import java.io.File;

public final class R01 extends JavaPlugin {
    private static PluginManager pm;
    private static UserIOHandler userIoHandler;
    private static ConfigIOHandler configIOHandler;

    public R01() {
        pm = getServer().getPluginManager();
        userIoHandler = UserIOHandler.getIOHandler();
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    public void fileLoader() {
        userIoHandler.fileLoader(checkUpDataFile(DataFile.USER_DATA));
        configIOHandler.fileLoader(checkUpDataFile(DataFile.CONFIG));
    }

    private File checkUpDataFile(DataFile string) {
        return new File(getDataFolder(), string.getFileName());
    }

    private void commandHandler() {
        this.getCommand("운석").setExecutor(new Meteor());
        this.getCommand("나").setExecutor(new VoChecker());
        this.getCommand("Motd").setExecutor(new MotdSet());
        this.getCommand("머리").setExecutor(new ArmourSet());
        this.getCommand("몸통").setExecutor(new ArmourSet());
        this.getCommand("바지").setExecutor(new ArmourSet());
        this.getCommand("신발").setExecutor(new ArmourSet());
        this.getCommand("god").setExecutor(new GodModeSet());
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("체력초기화").setExecutor(new HealthSet());
        this.getCommand("SaveUserData").setExecutor(new SaveUserData());
        this.getCommand("R01ConfigReload").setExecutor(
                new Reload(checkUpDataFile(DataFile.CONFIG)));
    }

    private void functionHandler() {
        pm.registerEvents(new Death(),this);
        pm.registerEvents(new Explosive(),this);
        pm.registerEvents(new HandSwing(),this);
        pm.registerEvents(new JoinAndQuit(),this);
        pm.registerEvents(new RaidAnnouncer(),this);
        pm.registerEvents(new RecipeController(), this);
        pm.registerEvents(new EntityDamageTicking(),this);
    }

    @Override
    public void onEnable() {
        this.commandHandler(); // command set
        this.functionHandler(); // function set
        this.saveDefaultConfig(); // dataFile set
        this.fileLoader(); // config set File
        configIOHandler.allConfigLoad(); //config Load
        userIoHandler.inputUserData(); // userData Set
    }

    @Override
    public void onDisable() {
        userIoHandler.outputUserData();
    }
}
