package teamzesa;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.DataBase.IOHandler.RObjectIOHandler;
import teamzesa.DataBase.UserKillStatusHandler.KillStatusController;
import teamzesa.DataBase.entity.RObject.User;
import teamzesa.DataBase.entity.RObject.UserKillStatus;
import teamzesa.DataBase.userHandler.UserController;
import teamzesa.event.EventRegister.EventRegisterSection;
import teamzesa.util.Announcer;
import teamzesa.util.Enum.CommandType;
import teamzesa.util.Enum.DataFile;
import teamzesa.util.ThreadPool;

import java.util.EnumSet;


public final class R01 extends JavaPlugin {
    private final String PLUGIN_NAME = "[R01]";
    private long RUNNING_TIME;

    @Override
    public void onLoad() {
        this.RUNNING_TIME = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
        registerCommandAndEvent();   //command set

//        setMaxPlayers(50);

//        saveDefaultSource
        if (BooleanUtils.isFalse(getDataFolder().exists()))
            generationDataFile();

//        configSet
        dataFileLoader();

//        speedLimiter();
        configFileLoader(); // config set File
        SchedulingExportData(); // User Data Auto Save Scheduling

//        loading Time
        pluginLoadTime();
    }

    @Override
    public void onDisable() {
        new RObjectIOHandler().exportData(
                DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName()
        );

        new RObjectIOHandler().exportData(
                DataFile.KILL_STATUS, new KillStatusController().getAllUserTable(), getClass().getName()
        );

        ThreadPool.getThreadPool().allServiceOff();
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void dataFileLoader() {
        new UserController().updateAllUserData(
                new RObjectIOHandler().importData(DataFile.USER_DATA, User.class, getClass().getName())
        );

        new KillStatusController().updateAllUserData(
                new RObjectIOHandler().importData(DataFile.KILL_STATUS, UserKillStatus.class, getClass().getName())
        );
    }

    public void configFileLoader() {
//        config Set
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        configIOHandler.fileLoader();
        configIOHandler.allConfigLoad(); //config Load

//        Announcer Set
        Announcer.getAnnouncer().defaultAnnouncer();
    }

    private void registerCommandAndEvent() {
        getServer().getPluginManager().registerEvents(new EventRegisterSection(), this); //function set

        EnumSet<CommandType> commandType = EnumSet.allOf(CommandType.class);
        commandType.forEach(c -> getCommand(c.getCommand()).setExecutor(c.getCommandInstance()));
    }

    private void SchedulingExportData() {
        long delay = 0;
        long interval = 720; // 12hour term auto save

        ThreadPool.getThreadPool().addSchedulingTaskMin(
                () -> {
                    new RObjectIOHandler().exportData(
                            DataFile.USER_DATA, new UserController().getAllUserTable(), getClass().getName()
                    );

                    new RObjectIOHandler().exportData(
                            DataFile.KILL_STATUS, new KillStatusController().getAllUserTable(), getClass().getName()
                    );
                },
                delay,
                interval
        );
        Bukkit.getLogger().info(PLUGIN_NAME + " Success Add Scheduling All R01 Object Data Save");
    }

    private void pluginLoadTime() {
        String blockingTime = String.format("(%.3fs)",
                (System.currentTimeMillis() - this.RUNNING_TIME) / 1000.0
        );
        Bukkit.getLogger().info(PLUGIN_NAME + " Done " + blockingTime);
    }

    private void generationDataFile() {
        saveDefaultConfig(); // config Data
        saveResource(DataFile.USER_DATA.getFileName(), false); // userData
        saveResource(DataFile.KILL_STATUS.getFileName(), false); // killStatusData
        Bukkit.getLogger().info(PLUGIN_NAME + " Plugin Data File 생성 완료.");
    }
}
