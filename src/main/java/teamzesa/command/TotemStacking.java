package teamzesa.command;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import teamzesa.announcer.ComponentExchanger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TotemStacking implements CommandExecutor {
    private final int MINIMUM = 1; // 합칠 수 있는 최소단위

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        List<ItemStack> playerItemStack = new ArrayList<>(
                Arrays.asList(player.getInventory().getContents()));

        // Checking total Totem amount
        List<Integer> itemList = playerItemStack.stream()
                .filter(item -> item != null && item.getType() == Material.TOTEM_OF_UNDYING)
                .map(ItemStack::getAmount)
                .collect(Collectors.toList());

        // validation totemCount
        if (validMinimumTotemCount(itemList)) {
            ComponentExchanger.playerAnnouncer(player,"2개 이상의 토템을 가지고 있으셔야 합니다.", "RED");
            return false;
        }

        if (validTotemCommand(itemList)) {
            ComponentExchanger.playerAnnouncer(player,"합칠 토템이 없습니다.", "RED");
            return false;
        }

        // offHandCheck
        if (player.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING)
            player.getInventory().setItemInOffHand(null);

        // remove Inventory
        playerItemStack.stream()
                .filter(item -> item != null && item.getType() == Material.TOTEM_OF_UNDYING)
                .forEach(item -> player.getInventory().remove(item));

        // setTotem
        int totalAmount = itemList.stream().mapToInt(Integer::intValue).sum();
//        p.sendMessage("총 토템 " + String.valueOf(totalAmount));
        ItemStack stackOfTotem = new ItemStack(Material.TOTEM_OF_UNDYING, totalAmount);
        player.getInventory().addItem(stackOfTotem);

        ComponentExchanger.playerAnnouncer(player,"합칠 토템이 없습니다.", "YELLOW");
        return true;
    }

    public boolean validMinimumTotemCount(List<Integer> list) {
        int cnt = (int) list.stream().filter(num -> num == 1).count();
        return list.stream()
                .noneMatch(num -> num > MINIMUM || cnt > MINIMUM);
    }

    public boolean validTotemCommand(List<Integer> list) {
        int cnt = (int) list.stream().filter(num -> num < 64).count();
        return list.stream()
                .noneMatch(num -> cnt > MINIMUM);
    }
}
