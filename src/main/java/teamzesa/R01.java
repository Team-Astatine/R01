package teamzesa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import teamzesa.combat.*;
import teamzesa.command.*;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.IOHandler.DataFile;
import teamzesa.IOHandler.UpdateChecker;
import teamzesa.userEvent.Death;
import teamzesa.worldSet.*;
import teamzesa.IOHandler.UserIOHandler;
import teamzesa.userEvent.JoinAndQuit;

import java.io.File;
import java.lang.reflect.Type;

public final class R01 extends JavaPlugin {
    private static PluginManager pm;
    private static Announcer announcer;
    private static UserIOHandler userIoHandler;
    private static ConfigIOHandler configIOHandler;
    private static UpdateChecker updateChecker;
    private static File pluginFiles;

    public R01() {
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

    @Contract("_ -> new")
//    어떤 파라미터든 객체를 반환하는 메서드 intellij annotation
    private @NotNull File checkUpdateFile(@NotNull DataFile value) {
        return new File(getDataFolder(), value.getFileName());
    }

    private void commandHandler() {
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("운석").setExecutor(new Meteor());
        this.getCommand("나").setExecutor(new UserObjectChecker());
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
//        pm.registerEvents(new Anvil(),this); // test
//        pm.registerEvents(new DisplayEntity(),this); // test

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

//        saveDefaultSource
        if (!getDataFolder().exists()) {
            this.saveDefaultConfig(); // config Data
            this.saveResource(DataFile.USER_DATA.getFileName(),false); // userData
            System.out.println("Plugin Data File 생성 완료.");
        }

//        configSet
        this.fileLoader(); // config set File
        configIOHandler.allConfigLoad(); //config Load

        userIoHandler.importUserData(); // userData Set
        announcer.defaultAnnouncer(1); // Announcer Set
        announcer.defaultAnnouncer(2); // Announcer Set
    }

    @Override
    public void onDisable() {
        userIoHandler.exportUserData();
    }
}
