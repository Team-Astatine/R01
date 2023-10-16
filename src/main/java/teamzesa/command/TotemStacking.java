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
    private Player player;
    private List<Integer> totemCountData;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        this.player = (Player) sender;

        getAllOfPlayerTotems();

        if (validationInventory()) 
            return false;

        removeTotemInInv();

        supplyTotems();

        playerAnnouncer(this.player,"토템을 합쳤습니다.", Color.YELLOW);
        return true;
    }

    private void getAllOfPlayerTotems() {
        List<ItemStack> playerItemStack = Arrays.asList(this.player.getInventory().getContents());
        this.totemCountData = playerItemStack.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getType().equals(TOTEM))
                .map(ItemStack::getAmount)
                .toList();
    }

    private void supplyTotems() {
        int totalAmount = totemCountData.stream()
                .mapToInt(Integer::intValue)
                .sum();

//        만약 한셋 이하면
        if (totalAmount <= STACK) {
            this.player.getInventory().setItemInOffHand(
                    new ItemStack(TOTEM,totalAmount)
            );
        } else {
//       한셋 그 이상이면
            this.player.getInventory().setItemInOffHand(new ItemStack(TOTEM, STACK));
            this.player.getInventory().addItem(new ItemStack(TOTEM, totalAmount - STACK));
        }
    }

    private void removeTotemInInv() {
        //        remove Inventory
        this.player.getInventory().remove(TOTEM);

//        전체 아이템 창 정보 다 가져옴 armour , offHand What ever
        Optional <ItemStack> tempHelmetStuff = Optional. ofNullable(this.player.getInventory().getHelmet());
        tempHelmetStuff.ifPresent(helmet -> {
//            System.out.println("totemHelmet > " + helmet);
            if (helmet.getType() == TOTEM)
                this.player.getInventory().setHelmet(null);
        });

        Optional <ItemStack> tempOffHandStuff = Optional.of(this.player.getInventory().getItemInOffHand());
        tempOffHandStuff.ifPresent(offhand -> {
//            System.out.println("totemOffHand > " + offhand);
            if (offhand.getType() == TOTEM)
                this.player.getInventory().setItemInOffHand(null);
        });
    }

    private boolean validationInventory() {
        String message = null;
        if (this.totemCountData.isEmpty())
            message = "인벤토리에 토템이 없습니다.";

        if (maxTotemCnt() < 2) 
            message = "합칠 토템이 없습니다.";

        if (maxTotemCnt() < 2 && minTotemCnt() < 2) 
            message = "2개 이상의 토템을 가지고 있으셔야 합니다.";
        
        if (message != null) {
            playerAnnouncer(this.player, message, Color.RED);
            return true;
        }
        
        return false;
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