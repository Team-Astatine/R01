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
    private final Material TOTEM = Material.TOTEM_OF_UNDYING;
    private final int MINIMUM = 1; // 합칠 수 있는 최소 단위 +1
    private final int STACK = 64;
    private List<Integer> totemCountData;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        Player player = (Player) sender;

        List<ItemStack> playerItemStack = Arrays.asList(player.getInventory().getContents());
        Optional <ItemStack> tempHelmetStuff = Optional.ofNullable(player.getInventory().getHelmet());
        Optional <ItemStack> tempOffHandStuff = Optional.of(player.getInventory().getItemInOffHand());
//        System.out.println(playerItemStack); // 전체 아이템 창 정보 다 가져옴 armour , offHand What ever

//        vaild Amount Of Totem
        this.totemCountData = playerItemStack.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getType().equals(TOTEM))
                .map(ItemStack::getAmount)
                .toList();

//        validation
        if (this.totemCountData.isEmpty()) {
            playerAnnouncer(player,"인벤토리에 토템이 없습니다.", Color.RED);
            return false;
        }

        if (maxTotemCnt() < 2) {
//            System.out.println(maxTotemCnt());
            playerAnnouncer(player,"합칠 토템이 없습니다.", Color.RED);
            return false;
        }

        if (maxTotemCnt() < 2 && minTotemCnt() < 2) {
//            System.out.println(minTotemCnt());
            playerAnnouncer(player,"2개 이상의 토템을 가지고 있으셔야 합니다.", Color.RED);
            return false;
        }

//        setTotem
        int totalAmount = totemCountData.stream()
                .mapToInt(Integer::intValue)
                .sum();
//        player.sendMessage("총 토템 " + totalAmount);

//        remove Inventory
        player.getInventory().remove(TOTEM);
//        System.out.println(Arrays.toString(player.getInventory().getContents()));

        tempHelmetStuff.ifPresent(helmet -> {
//            System.out.println("totemHelmet > " + helmet);
            if (helmet.getType() == TOTEM)
                player.getInventory().setHelmet(null);
        });

        tempOffHandStuff.ifPresent(offhand -> {
//            System.out.println("totemOffHand > " + offhand);
            if (offhand.getType() == TOTEM)
                player.getInventory().setItemInOffHand(null);
        });

//        만약 한셋 이하면
        if (totalAmount <= STACK) {
            player.getInventory().setItemInOffHand(
                    new ItemStack(TOTEM,totalAmount)
            );
        } else {
//       한셋 그 이상이면
            player.getInventory().setItemInOffHand(new ItemStack(TOTEM, STACK));
            player.getInventory().addItem(new ItemStack(TOTEM, totalAmount - STACK));
        }

        playerAnnouncer(player,"토템을 합쳤습니다.", Color.YELLOW);
        return true;
    }

    private long maxTotemCnt() {
        return this.totemCountData.stream()
                .filter(cnt -> cnt < STACK)
                .count();
    }

    private long minTotemCnt() {
        return this.totemCountData.stream()
                .filter(cnt -> cnt == MINIMUM)
                .count();
    }
}