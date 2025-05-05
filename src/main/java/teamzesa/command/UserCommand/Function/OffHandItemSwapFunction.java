package teamzesa.command.UserCommand.Function;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.CommandRegisterSection;
import teamzesa.Enumeration.Command.ListOfCommand;

public class OffHandItemSwapFunction extends CommandRegisterSection {

    public OffHandItemSwapFunction() {
        super(ListOfCommand.OFF_HAND_ITEM_SWAP_FOR_BE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();

        player.getInventory().setItemInMainHand(offHandItem);
        player.getInventory().setItemInOffHand(mainHandItem);

        return true;
    }
}
