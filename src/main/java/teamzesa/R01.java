package teamzesa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import teamzesa.IOHandler.*;
import teamzesa.command.*;
import teamzesa.resgisterEvent.CommandListup;
import teamzesa.resgisterEvent.EventListup;
import teamzesa.worldSet.*;

import java.io.File;
import java.util.Objects;

public final class R01 extends JavaPlugin {

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
        ThreadPool.getThreadPool().serviceOff();
        Bukkit.getScheduler().cancelTasks(this);
        UserIOHandler.getIOHandler().exportUserData();
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
        UpdateChecker.getUpdateChecker().fileLoader();
        UpdateChecker.getUpdateChecker().fileManager(); //checking update
    }

    private void commandHandler() {
        for (CommandListup commandEnum : CommandListup.values()) {
            Objects.requireNonNull(
                    getCommand(commandEnum.getCommand())).setExecutor(commandEnum.newInstance()
            );
        }
    }

    private void functionHandler() {
        PluginManager pm = getServer().getPluginManager();
        for (EventListup eventListup : EventListup.values())
            pm.registerEvents(eventListup.newInstance(),this);
    }

}
