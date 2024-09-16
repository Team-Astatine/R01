package teamzesa.command.Announcing;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.ArrayList;

public class ServerTip extends CommandRegisterSection {
    public ServerTip() {
        super(CommandExecutorMap.SERVER_TIP);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        ColorMap commonColor = ColorMap.RED;

        ArrayList<Component> serverTip = new ArrayList<>();
        serverTip.add(componentExchanger(configIOHandler.getSteelLifeTip(), commonColor));
        serverTip.add(componentExchanger(configIOHandler.getRaidTip(), commonColor));
        serverTip.add(componentExchanger(configIOHandler.getWeaponTip(), commonColor));
        serverTip.add(componentExchanger(configIOHandler.getExplosiveTip(), commonColor));

        for (Component comment : serverTip)
            playerSendMsgComponentExchanger((Player) commandSender, comment);

        return true;
    }
}
