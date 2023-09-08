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
    private ItemStack offHandStuff;
    private ItemStack helmetStuff;
    private int totalTotemCnt;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        Player player = (Player) sender;
//        armour inv + offHand
        List<ItemStack> playerItemStack = Arrays.asList(player.getInventory().getContents());

//        vaild Amount Of Totem
        List<Integer>totemCountData = totalTotemCntToList(playerItemStack);
//        System.out.println(totemCountData);

//        validation
        if (totemCountData.isEmpty()) {
            playerAnnouncer(player,"인벤토리에 토템이 없습니다.",Color.RED);
            return false;
        }

        if (validMinimumTotemCount(totemCountData)) {
            playerAnnouncer(player,"2개 이상의 토템을 가지고 있으셔야 합니다.", Color.RED);
            return false;
        }

        if (validTotemCommand(totemCountData)) {
            playerAnnouncer(player,"합칠 토템이 없습니다.", Color.RED);
            return false;
        }

        System.out.println(playerItemStack);
//        remove Inventory
        player.getInventory().remove(Material.TOTEM_OF_UNDYING);
        System.out.println(Arrays.toString(player.getInventory().getContents()));


//        먼저 토템인거 삭제 했으니 토템이면 공기로 바뀜, 즉 다른 아이템이면 AIR가 아닐테니 deep copy
        ItemStack tempOffHandStuff = player.getInventory().getItemInOffHand();
        ItemStack tempHeadStuff = player.getInventory().getHelmet();
        if (tempOffHandStuff.getType() != Material.AIR)
            offHandStuff = tempOffHandStuff.clone();
        if (tempHeadStuff.getType() != Material.AIR)
            helmetStuff = tempHeadStuff.clone();

//        만약 한셋 이하면
        if (totalTotemCnt <= 64) {
            player.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING,totalTotemCnt));
        }

//       한셋 그 이상이면
        if (totalTotemCnt > 64){
            player.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING, MAX_STACK));
            player.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING, totalTotemCnt - MAX_STACK));
        }

        player.getInventory().addItem(offHandStuff);
        player.getInventory().addItem(helmetStuff);
        playerAnnouncer(player,"토템을 합쳤습니다.", Color.YELLOW);
        return true;
    }

    private List<Integer> totalTotemCntToList(List<ItemStack> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getType().equals(Material.TOTEM_OF_UNDYING))
                .map(ItemStack::getAmount)
                .toList();
    }

    private boolean validMinimumTotemCount(List<Integer> list) {
        totalTotemCnt = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        return totalTotemCnt == MINIMUM;
    }

    private boolean validTotemCommand(List<Integer> list) {
//        1이 들어가 있지 않고
        boolean singleCnt = !list.contains(MINIMUM);

//        64개 미만인 값을 count 해서
        long nonMaxStack = list.stream()
                .filter(cnt -> cnt < MAX_STACK)
                .count();

//        1이 들어가 있지 않으며, 64개 미만 값이 1이면 return
        return singleCnt && nonMaxStack == 1;
    }
}
