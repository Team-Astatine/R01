package teamzesa.command.ModeratorCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.CommandType;


public class LookUserMainHandItem extends CommandRegisterSection {
    public LookUserMainHandItem() {
        super(CommandType.LOOK_USER_MAIN_HAND_ITEM);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ItemStack mainHandItem = ((Player) commandSender).getInventory().getItemInMainHand();
        System.out.println("mainHandItem > " + mainHandItem.toString());
        playerSendMsgComponentExchanger(commandSender, mainHandItem.toString(), ColorList.YELLOW);
        return true;
    }
}
