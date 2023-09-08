package teamzesa.command;

import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TotemStacking extends ComponentExchanger implements CommandExecutor {
    private final int MINIMUM = 1; // 합칠 수 있는 최소 단위 +1
    private final int MAX_STACK = 64;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        Player player = (Player) sender;
//        armour inv + offHand
        List<ItemStack> playerItemStack = Arrays.asList(player.getInventory().getContents());

//        vaild Amount Of Totem
        List<Integer>totemCountData = playerItemStack.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getType().equals(Material.TOTEM_OF_UNDYING))
                .map(ItemStack::getAmount)
                .toList();

//        System.out.println(totemCountData);


        if (validTotemCommand(totemCountData)) {
            playerAnnouncer(player,"합칠 토템이 없습니다.", Color.RED);
            return false;
        }

//        validation
        if (validMinimumTotemCount(totemCountData)) {
            playerAnnouncer(player,"2개 이상의 토템을 가지고 있으셔야 합니다.", Color.RED);
            return false;
        }

//        offHandCheck 토템제공 후 addItem 예정
        ItemStack tempOffHandStuff = player.getInventory().getItemInOffHand();
        if (tempOffHandStuff.getType() == Material.TOTEM_OF_UNDYING) {
            player.getInventory().setItemInOffHand(null);
            tempOffHandStuff = null;
        }

//        remove Inventory
        playerItemStack.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getType() == Material.TOTEM_OF_UNDYING)
                .forEach(item -> player.getInventory().remove(item));

//        setTotem
        int totalAmount = totemCountData.stream()
                .mapToInt(Integer::intValue)
                .sum();
//        player.sendMessage("총 토템 " + totalAmount);

//        만약 한셋 이하면
        if (totalAmount <= 64) {
            player.getInventory().setItemInOffHand(
                    new ItemStack(Material.TOTEM_OF_UNDYING,totalAmount)
            );
        } else {
//       한셋 그 이상이면
            player.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING, MAX_STACK));
            player.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING, totalAmount - MAX_STACK));
        }

        player.getInventory().addItem(tempOffHandStuff);
        playerAnnouncer(player,"토템을 합쳤습니다.", Color.YELLOW);
        return true;
    }

    public boolean validMinimumTotemCount(List<Integer> list) {
        long cnt = list.stream().filter(num -> num == 1).count();
        return list.stream().noneMatch(num -> num > MINIMUM || cnt > MINIMUM);
    }

    public boolean validTotemCommand(List<Integer> list) {
        boolean singleCnt = !list.contains(MINIMUM);
        long nonMaxStack = list.stream()
                .filter(cnt -> cnt < MAX_STACK)
                .count();

        return singleCnt && nonMaxStack == 1;
    }
}
