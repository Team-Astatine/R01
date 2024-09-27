package teamzesa.command.AdminCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandType;


public class ValueItemStackChecker extends CommandRegisterSection {
    public ValueItemStackChecker() {
        super(CommandType.HAND_ITEM_CHECKER);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ItemStack mainHandItem = ((Player) commandSender).getInventory().getItemInMainHand();
        playerSendMsgComponentExchanger(commandSender, mainHandItem.toString(), ColorList.YELLOW);
        return true;
    }
}
