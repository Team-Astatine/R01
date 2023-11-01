package teamzesa.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.dataValue.ColorList;
import teamzesa.resgisterEvent.EventExecutor;

import java.util.*;
import java.util.List;

public class TotemStacking implements CommandExecutor, EventExecutor {
    private final Material TOTEM = Material.TOTEM_OF_UNDYING;
    private final int STACK = 64;
    private final int MINIMUM = 1; // 합칠 수 있는 최소 단위 +1
    private Player player;
    private PlayerInventory playerInventory;
    private List<Integer> totemCountData;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        this.player = (Player) sender;
        this.playerInventory = this.player.getInventory();
        getAllOfPlayerTotems();

        if (validationInventory()) return false;
        removeTotemInInv();
        supplyTotems();
        return true;
    }

    private void getAllOfPlayerTotems() {
        this.totemCountData = Arrays.stream(this.playerInventory.getContents())
                .filter(Objects::nonNull)
                .filter(item -> item.getType().equals(TOTEM))
                .map(ItemStack::getAmount)
                .toList();
    }

    private void supplyTotems() {
        int totalAmount = totemCountData.stream()
                .mapToInt(Integer::intValue)
                .sum();

//        n <= STACK
        if (totalAmount <= STACK) {
            this.playerInventory.setItemInOffHand(new ItemStack(TOTEM,totalAmount));
        } else {
//        n >= STACK
            this.playerInventory.setItemInOffHand(new ItemStack(TOTEM, STACK));
            this.playerInventory.addItem(new ItemStack(TOTEM, totalAmount - STACK));
        }

       ComponentExchanger.playerAnnouncer(this.player,"토템을 합쳤습니다.", ColorList.YELLOW);
    }

    private void removeTotemInInv() {
        this.playerInventory.remove(TOTEM);

//        전체 아이템 창 정보 다 가져옴 armour , offHand Whatever
        Optional.ofNullable(this.playerInventory.getHelmet())
                .ifPresent(helmetStuff -> {
                    if (helmetStuff.getType() == TOTEM)
                        this.playerInventory.setHelmet(null);
                });

        Optional.of(this.playerInventory.getItemInOffHand())
                .ifPresent(offhandStuff -> {
                    if (offhandStuff.getType() == TOTEM)
                        this.playerInventory.setItemInOffHand(null);
                    else
                        this.playerInventory.addItem(offhandStuff);
                });
    }

    private boolean validationInventory() {
        String message = "";

        if (this.totemCountData.isEmpty())
            message = "인벤토리에 토템이 없습니다.";

        else if (maxTotemCnt() < 2 && minTotemCnt() < 1)
            message = "합칠 토템이 없습니다.";

        else if (maxTotemCnt() < 2 && minTotemCnt() < 2)
            message = "2개 이상의 토템을 가지고 있으셔야 합니다.";

        if (message.isEmpty())
            return false;

        ComponentExchanger.playerAnnouncer(this.player, message, ColorList.RED);
        return true;
    }

    private long maxTotemCnt() {
        return this.totemCountData.stream()
                .filter(cnt -> cnt >= MINIMUM && cnt < STACK)
                .count();
    }

    private long minTotemCnt() {
        return this.totemCountData.stream()
                .filter(cnt -> cnt == MINIMUM)
                .count();
    }
}