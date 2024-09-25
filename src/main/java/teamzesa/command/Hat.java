package teamzesa.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.Enum.ColorList;

public record Hat() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final String[] args) {

        Player player = (Player) sender;
        PlayerInventory playerInventory = player.getInventory();
        ItemStack armourHead = playerInventory.getHelmet();

        ItemStack tmpItemInHand = playerInventory.getItemInMainHand();
        if (tmpItemInHand.isEmpty()) {
            player.sendMessage(Component.text("손에 아이템이 없습니다.", ColorList.RED.getTextColor()));
            return false;
        }

        playerInventory.setHelmet(tmpItemInHand);
        playerInventory.setItemInMainHand(armourHead);
        player.sendMessage(Component.text("머리에 썼어요!", ColorList.YELLOW.getTextColor()));
        return true;
    }
}
