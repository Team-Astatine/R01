package teamzesa;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.*;
import teamzesa.command.*;
import teamzesa.IOHandler.config.ConfigIOHandler;
import teamzesa.IOHandler.DataFile;
import teamzesa.IOHandler.update.UpdateChecker;
import teamzesa.userValue.Death;
import teamzesa.worldSet.DisplayEntity;
import teamzesa.worldSet.RaidAnnouncer;
import teamzesa.IOHandler.userHandler.UserIOHandler;
import teamzesa.userValue.JoinAndQuit;
import teamzesa.worldSet.RecipeController;

import java.io.File;

public final class R01 extends JavaPlugin {
    private static PluginManager pm;
    private static UserIOHandler userIoHandler;
    private static ConfigIOHandler configIOHandler;
    private static UpdateChecker updateChecker;
    private static File pluginFiles;

    public R01() {
        pm = getServer().getPluginManager();
        userIoHandler = UserIOHandler.getIOHandler();
        updateChecker = UpdateChecker.getUpdateChecker();
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
        pluginFiles = new File(getDataFolder().getParentFile().getAbsolutePath());
    }

    public void fileLoader() {
        configIOHandler.fileLoader(checkUpDataFile(DataFile.CONFIG));
        userIoHandler.fileLoader(checkUpDataFile(DataFile.USER_DATA));
    }

    public void updateCheck() {
        updateChecker.fileLoader(pluginFiles);
        updateChecker.fileManager(); //checking update
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
        this.getCommand("관리자").setExecutor(new Moderator());
        this.getCommand("토템").setExecutor(new TotemStacking());
        this.getCommand("체력초기화").setExecutor(new HealthSet());
        this.getCommand("SaveUserData").setExecutor(new SaveUserData());
        this.getCommand("R01ConfigReload").setExecutor(
                new Reload(checkUpDataFile(DataFile.CONFIG)));
    }

    private void functionHandler() {
        pm.registerEvents(new DisplayEntity(),this);
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

//        update check
        this.updateCheck();

//        configSet
        this.fileLoader(); // config set File
        userIoHandler.inputUserData(); // userData Set

//        dataSet
        this.saveDefaultConfig(); // dataFile set
        configIOHandler.allConfigLoad(); //config Load
    }

    @Override
    public void onDisable() {
        userIoHandler.outputUserData();
    }
}
