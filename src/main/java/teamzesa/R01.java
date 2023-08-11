package teamzesa;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.combat.*;
import teamzesa.command.*;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.IOHandler.DataFile;
import teamzesa.IOHandler.UpdateChecker;
import teamzesa.userValue.Death;
import teamzesa.worldSet.Announcer;
import teamzesa.worldSet.DisplayEntity;
import teamzesa.worldSet.RaidAnnouncer;
import teamzesa.IOHandler.UserIOHandler;
import teamzesa.userValue.JoinAndQuit;
import teamzesa.worldSet.RecipeController;

import java.io.File;

public final class R01 extends JavaPlugin {
    private static PluginManager pm;
    private static UserIOHandler userIoHandler;
    private static ConfigIOHandler configIOHandler;
    private static UpdateChecker updateChecker;
    private static File pluginFiles;
    private static Announcer announcer;

    public R01() {
        this.saveDefaultConfig(); // dataFile set
        pm = getServer().getPluginManager();
        announcer = Announcer.getAnnouncer();
        userIoHandler = UserIOHandler.getIOHandler();
        updateChecker = UpdateChecker.getUpdateChecker();
        configIOHandler = ConfigIOHandler.getConfigIOHandler();
        pluginFiles = new File(getDataFolder().getParentFile().getAbsolutePath());
    }

    public void fileLoader() {
        configIOHandler.fileLoader(checkUpdateFile(DataFile.CONFIG));
        userIoHandler.fileLoader(checkUpdateFile(DataFile.USER_DATA));
    }

    public void updateCheck() {
        updateChecker.fileLoader(pluginFiles);
        updateChecker.fileManager(); //checking update
    }

    private File checkUpdateFile(DataFile value) {
        return new File(getDataFolder(), value.getFileName());
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
                new Reload(checkUpdateFile(DataFile.CONFIG)));
    }

    private void functionHandler() {
        pm.registerEvents(new DisplayEntity(),this); // test
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
        configIOHandler.allConfigLoad(); //config Load
        announcer.defaultAnnouncer(this); // Announcer Set
    }

    @Override
    public void onDisable() {
        userIoHandler.outputUserData();
    }
}
