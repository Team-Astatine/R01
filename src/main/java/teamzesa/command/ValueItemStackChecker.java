package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.ArrayList;
import java.util.List;


public class ValueItemStackChecker extends CommandRegisterSection {
    public ValueItemStackChecker() {
        super(CommandExecutorMap.HAND_ITEM_CHECKER);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ItemStack mainHandItem = ((Player) commandSender).getInventory().getItemInMainHand();
        playerSendMsgComponentExchanger(commandSender, mainHandItem.toString(), ColorMap.YELLOW);
        return new ArrayList<>(List.of("vi"));
    }
}
