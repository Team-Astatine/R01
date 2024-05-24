package teamzesa;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.IOHandler.Announcer;
import teamzesa.event.EventRegister.EventRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.IOHandler.ConfigIOHandler;
import teamzesa.util.userHandler.UserIOHandler;
import teamzesa.util.ThreadPool;

import java.util.*;


public final class R01 extends JavaPlugin {
    private final String PLUGIN_NAME = "[R01]";
    private long RUNNING_TIME;

    @Override
    public void onLoad() {
        this.RUNNING_TIME = System.currentTimeMillis();
    }

    @Override
    public void onEnable() {
        eventAndFunctionRegister();   //command set

//        saveDefaultSource
        if (!getDataFolder().exists())
            generationDataFile();

//        configSet
        userDataLoader();
//        speedLimiter();
        configFileLoader(); // config set File
        autoSaveSchedule(); // User Data Auto Save Scheduling

//        loading Time
        pluginLoadTime();
    }

    @Override
    public void onDisable() {
        UserIOHandler.exportUserData("Disable Server");
        ThreadPool.getThreadPool().allServiceOff();
        Bukkit.getScheduler().cancelTasks(this);
    }

    public void userDataLoader() {
//        import userData
        UserIOHandler.importUserData("Starting Server");
    }

    public void speedLimiter() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                () -> {
                    double allowedMaxMetersPerSec = 110.0;
                    Iterator playersIterator = Bukkit.getOnlinePlayers().iterator();

                    Set<String> tped = new HashSet();
                    HashMap<String, Location> locs = new HashMap();

                    while (true) {
                        while (true) {
                            Player player;
                            do {
                                if (!playersIterator.hasNext()) {
                                    return;
                                }

                                player = (Player) playersIterator.next();
                            } while (player.isOp());

                            if (locs.get(player.getName()) != null && !tped.contains(player.getName())) {
                                Location prevloc = (locs.get(player.getName())).clone();
                                Location newloc = player.getLocation().clone();
                                System.out.println(prevloc);
                                System.out.println(newloc);

                                Vector v = newloc.subtract(prevloc).toVector();
                                if (v.clone().normalize().getY() < -0.95) {
                                    locs.remove(player.getName());
                                    continue;
                                }

                                double distance = v.length();
                                if (distance > allowedMaxMetersPerSec) {
                                    if (player.isInsideVehicle()) {
                                        Entity vehicle = player.getVehicle();
                                        player.leaveVehicle();
                                        Location entityLoc = prevloc.clone().add(0.0, 0.5, 0.0);
                                        vehicle.teleport(entityLoc);
                                        player.teleport(prevloc);
                                    } else {
                                        player.teleport(prevloc);
                                    }

                                    player.sendMessage(
                                            Component.text("너무 빠릅니다.")
                                                    .color(ColorMap.RED.getTextColor())
                                    );
                                    continue;
                                }
                            }

                            locs.put(player.getName(), player.getLocation().clone());
                            tped.remove(player.getName());
                        }
                    }
                },
                0L,
                20L
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

    private void eventAndFunctionRegister() {
        getServer().getPluginManager().registerEvents(new EventRegisterSection(), this); //function set

        for (CommandExecutorMap commandEnum : CommandExecutorMap.values()) //command set
            getCommand(commandEnum.getCommand()).setExecutor(commandEnum.newInstance());
    }

    private void autoSaveSchedule() {
        long delay = 0;
        long interval = 720; // 12hour term auto save

        ThreadPool.getThreadPool().addSchedulingTaskMin(
                () -> UserIOHandler.exportUserData("Auto Saving"),
                delay,
                interval
        );
        Bukkit.getLogger().info(PLUGIN_NAME + " Success Add Scheduling All User Data Save");
    }

    private void pluginLoadTime() {
        String blockingTime = String.format("(%.3fs)",
                (System.currentTimeMillis() - this.RUNNING_TIME)/1000.0
        );
        Bukkit.getLogger().info(PLUGIN_NAME + " Done " + blockingTime);
    }

    private void generationDataFile() {
        saveDefaultConfig(); // config Data
        saveResource("userData.json" ,false); // userData
        Bukkit.getLogger().info(PLUGIN_NAME + " Plugin Data File 생성 완료.");
    }

}
