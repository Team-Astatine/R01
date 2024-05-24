package teamzesa;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import teamzesa.util.IOHandler.Announcer;
import teamzesa.event.EventRegister.EventRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.IOHandler.ConfigIOHandler;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.userHandler.UserIOHandler;
import teamzesa.util.ThreadPool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


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
        speedLimiter();
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
        ThreadPool.getThreadPool().addSchedulingTaskMin(
                () -> {
                    double allowedMaxMetersPerSec = 0.0;
                    Iterator var3 = Bukkit.getOnlinePlayers().iterator();

                    Set<String> tped = new HashSet();
                    HashMap<String, Location> locs = new HashMap();

                    while(true) {
                        while(true) {
                            Player player;
                            do {
                                if (!var3.hasNext()) {
                                    return;
                                }

                                player = (Player)var3.next();
                            } while(player.hasPermission("speedlimit.bypass"));

                            if (locs.get(player.getName()) != null && !tped.contains(player.getName())) {
                                Location prevloc = ((Location)locs.get(player.getName())).clone();
                                Location newloc = player.getLocation().clone();
                                if (getConfig().getBoolean("only-flying") && !player.isFlying() || getConfig().getBoolean("only-on-ground") && player.isFlying() || !getConfig().getList("worlds").contains(prevloc.getWorld().getName()) && !getConfig().getList("worlds").contains(newloc.getWorld().getName())) {
                                    continue;
                                }

                                Vector v = newloc.subtract(prevloc).toVector();
                                if (getConfig().getBoolean("allow-falling-bypass") && v.clone().normalize().getY() < -0.95) {
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
                                        if (getConfig().getBoolean("put-back-on-vehicle")) {
                                            vehicle.addPassenger(player);
                                        } else {
                                            player.teleport(prevloc);
                                        }
                                    } else {
                                        player.teleport(prevloc);
                                    }

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
