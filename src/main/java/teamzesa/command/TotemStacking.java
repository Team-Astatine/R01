package teamzesa.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TotemStacking extends ComponentExchanger implements CommandExecutor {
    private final int MINIMUM = 1; // 합칠 수 있는 최소 단위 +1
    private final int STACK = 64;
    private final int TOTAL_TOTEM_LIMIT = STACK * 9 * 4;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        Player player = (Player) sender;

        ItemStack[] inventoryContents = player.getInventory().getContents();
        int totalTotemCount = Arrays.stream(inventoryContents)
                .filter(s -> s.getType() == Material.TOTEM_OF_UNDYING)
                .mapToInt(ItemStack::getAmount)
                .sum();

        if (totalTotemCount < MINIMUM) {
            playerAnnouncer(player, "인벤토리에 토템이 없습니다.", Color.RED);
            return false;
        }

        if (totalTotemCount == MINIMUM) {
            playerAnnouncer(player, "합칠 토템이 없습니다.", Color.RED);
            return false;
        }

        if (totalTotemCount > TOTAL_TOTEM_LIMIT) {
            playerAnnouncer(player, "토템이 한도를 초과합니다.", Color.RED);
            return false;
        }

        int fullStacks = totalTotemCount / STACK;
        int remainder = totalTotemCount % STACK;
        boolean hasRemainder = remainder > 0;

        // Remove all totems from inventory
        player.getInventory().remove(new ItemStack(Material.TOTEM_OF_UNDYING, totalTotemCount));

        // Create stacks
        int stackCount = fullStacks + (hasRemainder ? remainder : 0);
        ItemStack[] stacks = new ItemStack[stackCount];

        int i = 0;

        for (; i < fullStacks; i++) {
            stacks[i] = new ItemStack(Material.TOTEM_OF_UNDYING, STACK);
        }

        if (hasRemainder) {
            stacks[i] = new ItemStack(Material.TOTEM_OF_UNDYING, remainder);
        }

        player.getInventory().addItem(stacks);

        playerAnnouncer(player,"토템을 합쳤습니다.", Color.YELLOW);
        return true;
    }
}