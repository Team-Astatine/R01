package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.enhance.EnhanceInventoryHandler;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.event.Enhance.PlayerInteraction.UserInterface.EnhanceUIGenerator;
import teamzesa.util.Enum.CommandType;



public class EnhanceDialog extends CommandRegisterSection {

    public EnhanceDialog() {
        super(CommandType.ENHANCE);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        Inventory enhanceUI = new EnhanceUIGenerator()
                .setSendPlayer(player)
                .generatingEnhanceUIItem()
                .executeUI();

        EnhanceInventoryHandler.getEnhanceInventoryHandler()
                .insert(player.getUniqueId(), enhanceUI);

        player.openInventory(enhanceUI);
        return true;
    }
}
