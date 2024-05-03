package teamzesa.event;

import org.bukkit.event.player.PlayerQuitEvent;
import teamzesa.entity.User;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.userHandler.UserController;

public class QuitMsgEvent extends StringComponentExchanger implements EventRegister {
    private User quitUser;
    private final PlayerQuitEvent quitEvent;

    public QuitMsgEvent(PlayerQuitEvent event) {
        this.quitEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        UserController userController = new UserController();
        this.quitUser = userController.readUser(this.quitEvent.getPlayer());
    }

    @Override
    public void execute() {
        if (this.quitUser.killCount() == 0)
            this.quitEvent.quitMessage(
                    componentExchanger(" - " + this.quitUser.name(), ColorMap.RED)
            );

        else
            this.quitEvent.quitMessage(
                    componentExchanger(" - [ " + this.quitUser.killCount() + "KILL ] " + this.quitUser.name(), ColorMap.RED)
            );
    }
}
//debug
/*
[06:41:13 ERROR]: Could not pass event PlayerQuitEvent to R01 v4.3
java.lang.NullPointerException: Cannot invoke "teamzesa.entity.User.killCount()" because "this.quitUser" is null
        at teamzesa.event.QuitMsgEvent.execute(QuitMsgEvent.java:29) ~[R01-4.3.jar:?]
        at teamzesa.event.QuitMsgEvent.<init>(QuitMsgEvent.java:18) ~[R01-4.3.jar:?]
        at teamzesa.event.register.EventRegisterSection.quitEvent(EventRegisterSection.java:58) ~[R01-4.3.jar:?]
        at com.destroystokyo.paper.event.executor.asm.generated.GeneratedEventExecutor467.execute(Unknown Source) ~[?:?]
        at org.bukkit.plugin.EventExecutor$2.execute(EventExecutor.java:77) ~[purpur-api-1.20.4-R0.1-SNAPSHOT.jar:?]
        at co.aikar.timings.TimedEventExecutor.execute(TimedEventExecutor.java:77) ~[purpur-api-1.20.4-R0.1-SNAPSHOT.jar:git-Purpur-2149]
        at org.bukkit.plugin.RegisteredListener.callEvent(RegisteredListener.java:70) ~[purpur-api-1.20.4-R0.1-SNAPSHOT.jar:?]
        at io.papermc.paper.plugin.manager.PaperEventManager.callEvent(PaperEventManager.java:54) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at io.papermc.paper.plugin.manager.PaperPluginManagerImpl.callEvent(PaperPluginManagerImpl.java:126) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at org.bukkit.plugin.SimplePluginManager.callEvent(SimplePluginManager.java:617) ~[purpur-api-1.20.4-R0.1-SNAPSHOT.jar:?]
        at net.minecraft.server.players.PlayerList.remove(PlayerList.java:614) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at net.minecraft.server.players.PlayerList.remove(PlayerList.java:598) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at net.minecraft.server.network.ServerGamePacketListenerImpl.removePlayerFromWorld(ServerGamePacketListenerImpl.java:2164) ~[?:?]
        at net.minecraft.server.network.ServerGamePacketListenerImpl.onDisconnect(ServerGamePacketListenerImpl.java:2144) ~[?:?]
        at net.minecraft.server.network.ServerGamePacketListenerImpl.onDisconnect(ServerGamePacketListenerImpl.java:2131) ~[?:?]
        at net.minecraft.network.Connection.handleDisconnection(Connection.java:846) ~[?:?]
        at net.minecraft.server.network.ServerConnectionListener.tick(ServerConnectionListener.java:254) ~[?:?]
        at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1771) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at net.minecraft.server.dedicated.DedicatedServer.tickChildren(DedicatedServer.java:487) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1548) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1236) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:323) ~[purpur-1.20.4.jar:git-Purpur-2149]
        at java.lang.Thread.run(Thread.java:1583) ~[?:?]
 */