package teamzesa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.IOHandler.*;
import teamzesa.resgisterEvent.CommandListup;
import teamzesa.resgisterEvent.EventListup;
import teamzesa.worldSet.*;


public final class R01 extends JavaPlugin {
    private static final double PLUGIN_VERSION = 3.0;

    @Override
    public void onEnable() {
        updateCheck(); // update check
        commandHandler(); // command set
        functionHandler(); // function set

//        saveDefaultSource
        if (!getDataFolder().exists())
            generationDataFile();

//        configSet
        fileLoader(); // config set File
    }

    private void generationDataFile() {
        saveDefaultConfig(); // config Data
        saveResource("userData.json" ,false); // userData
        Bukkit.getLogger().info("[R01] Plugin Data File 생성 완료.");
    }

    @Override
    public void onDisable() {
        UserIOHandler.getIOHandler().exportUserData();
        ThreadPool.getThreadPool().serviceOff();
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
        Bukkit.getLogger().info("[R01]Current Version -> " + PLUGIN_VERSION);
        UpdateChecker.getUpdateChecker().fileLoader();
        UpdateChecker.getUpdateChecker().fileManager(); //checking update
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
