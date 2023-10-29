package teamzesa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.*;
import teamzesa.IOHandler.ConfigIOHandler;
import teamzesa.IOHandler.DataFilePath;
import teamzesa.IOHandler.UpdateChecker;
import teamzesa.resgisterEvent.CommandListup;
import teamzesa.resgisterEvent.EventListup;
import teamzesa.worldSet.*;
import teamzesa.IOHandler.UserIOHandler;

import java.io.File;

public final class R01 extends JavaPlugin {
    private final UserIOHandler userIoHandler;
    private final ConfigIOHandler configIOHandler;
    private final UpdateChecker updateChecker;

    public R01() {
        this.userIoHandler = UserIOHandler.getIOHandler();
        this.updateChecker = UpdateChecker.getUpdateChecker();
        this.configIOHandler = ConfigIOHandler.getConfigIOHandler();
    }

    public void fileLoader() {
        this.configIOHandler.fileLoader(checkUpdateFile(DataFilePath.CONFIG));
        this.userIoHandler.fileLoader(checkUpdateFile(DataFilePath.USER_DATA));
    }

    public void updateCheck() {
        File pluginFiles = new File(getDataFolder().getParentFile().getAbsolutePath());
        this.updateChecker.fileLoader(pluginFiles);
        this.updateChecker.fileManager(); //checking update
    }

    @Contract("_ -> new")
//    어떤 파라미터든 객체를 반환하는 메서드 intellij annotation
    private @NotNull File checkUpdateFile(@NotNull DataFilePath value) {
        return new File(getDataFolder(), value.getFileInstance());
    }

    private void commandHandler() {
        for (CommandListup commandEnum : CommandListup.values()) {
            this.getCommand(commandEnum.getCommand()).setExecutor(commandEnum.newInstance());
        }

        this.getCommand("R01ConfigReload")
                .setExecutor(new Reload(checkUpdateFile(DataFilePath.CONFIG)));
    }

    private void functionHandler() {
        PluginManager pm = getServer().getPluginManager();
        for (EventListup eventListup : EventListup.values())
            pm.registerEvents(eventListup.newInstance(),this);
    }

    @Override
    public void onEnable() {
//        update check
        this.updateCheck();

        this.commandHandler(); // command set
        this.functionHandler(); // function set

//        saveDefaultSource
        if (!getDataFolder().exists()) {
            this.saveDefaultConfig(); // config Data
            this.saveResource(DataFilePath.USER_DATA.getFileInstance(),false); // userData
            Bukkit.getLogger().info("R01 -> Plugin Data File 생성 완료.");
        }

//        configSet
        this.fileLoader(); // config set File
        configIOHandler.allConfigLoad(); //config Load

        userIoHandler.importUserData(); // userData Set
        Announcer.getAnnouncer().defaultAnnouncer(); // Announcer Set
    }

    @Override
    public void onDisable() {
        ThreadPool.getThreadPool().serviceOff();
        userIoHandler.exportUserData();
        Bukkit.getScheduler().cancelTasks(this);
    }
}
