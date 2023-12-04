package teamzesa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.event.Announcer;
import teamzesa.util.Enum.CommandListup;
import teamzesa.util.Enum.EventListup;
import teamzesa.util.IOHandler.ConfigIOHandler;
import teamzesa.util.IOHandler.AutoUpdate;
import teamzesa.util.userHandler.UserIOHandler;
import teamzesa.util.ThreadPool;


public final class R01 extends JavaPlugin {
    private final long RUNNING_TIME;

    public R01() {
        this.RUNNING_TIME = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
//        updateCheck(); // update check
        commandHandler(); // command set
        functionHandler(); // function set

//        saveDefaultSource
        if (!getDataFolder().exists())
            generationDataFile();

//        configSet
        fileLoader(); // config set File

//        loading Time
        pluginLoadTime();
    }

    private void pluginLoadTime() {
        String blockingTime = String.format("(%.3fs)",
                (System.currentTimeMillis() - this.RUNNING_TIME)/1000.0
        );
        Bukkit.getLogger().info("[R01] Done" + blockingTime);
    }

    private void generationDataFile() {
        saveDefaultConfig(); // config Data
        saveResource("userData.json" ,false); // userData
        Bukkit.getLogger().info("[R01] Plugin Data File 생성 완료.");
    }

    @Override
    public void onDisable() {
        UserIOHandler.getIOHandler().exportUserData();
        ThreadPool.getThreadPool().allServiceOff();
        Bukkit.getScheduler().cancelTasks(this);
    }

    public void fileLoader() {
//        config Set
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        configIOHandler.fileLoader();
        configIOHandler.allConfigLoad(); //config Load

//        import userData
        UserIOHandler.getIOHandler().importUserData();

//        Announcer Set
        Announcer.getAnnouncer().defaultAnnouncer();
    }

    public void updateCheck() {
        AutoUpdate.getUpdateChecker().fileLoader();
        AutoUpdate.getUpdateChecker().fileManager(); //checking update
    }

    private void commandHandler() {
        for (CommandListup commandEnum : CommandListup.values()) {
            getCommand(commandEnum.getCommand()).setExecutor(commandEnum.newInstance());
        }
    }

    private void functionHandler() {
        PluginManager pm = getServer().getPluginManager();
        for (EventListup eventListup : EventListup.values())
            pm.registerEvents(eventListup.newInstance(),this);
    }

}
