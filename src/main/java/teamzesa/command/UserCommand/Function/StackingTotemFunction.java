package teamzesa.command.UserCommand.Function;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.CommandRegisterSection;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Enumeration.Command.ListOfCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StackingTotemFunction extends CommandRegisterSection {
    private final Material TOTEM = Material.TOTEM_OF_UNDYING;

    private final int STACK = 64;
    private final int MINIMUM = 1; // 합칠 수 있는 최소 단위 +1

    private Player player;
    private PlayerInventory playerInventory;
    private List<Integer> totemCountData;

    public StackingTotemFunction() {
        super(ListOfCommand.TOTEM_STACKING);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Runnable executeTotemSupply = () -> {
            StackingTotemFunction.this.player = (Player) sender;
            StackingTotemFunction.this.playerInventory = StackingTotemFunction.this.player.getInventory();
            getAllOfPlayerTotems();

            if (BooleanUtils.isFalse(isNotAllowedThisEvent()))
                return;

            removeTotemInInv();
            supplyTotems();
        };

        executeTotemSupply.run();
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
        if (totalAmount <= STACK)
            this.playerInventory.setItemInOffHand(new ItemStack(TOTEM, totalAmount));

        else {
//        n >= STACK
            /*
            total Amount / STACK  -> 몇으로 나눠지는지 확인
            인벤토리 공간을 확인 후 count
            첫 스택은 offHand에, 나머지 나눠지는 개수만큼 스택으로 지급 나머지는 개수로 지급
             */
            int supplyStackAmount = totalAmount / STACK;
            long invNullingSpaceCount = Arrays.stream(this.playerInventory.getContents())
                    .filter(Objects::isNull)
                    .count();

//            공간확보
            if (invNullingSpaceCount < supplyStackAmount + 1) {
                playerSendMsgComponentExchanger(this.player, "인벤토리 공간이 부족합니다.", ColorType.YELLOW);
                return;
            }

            /*
            OffHand 1스택
            나머지 인벤토리에 지급
             */
            this.playerInventory.setItemInOffHand(new ItemStack(TOTEM, STACK));
            this.playerInventory.addItem(new ItemStack(TOTEM, totalAmount - (supplyStackAmount * STACK)));
        }

        playerSendMsgComponentExchanger(this.player, "토템을 합쳤습니다.", ColorType.YELLOW);
    }

    private void removeTotemInInv() {
        this.playerInventory.remove(TOTEM);

//        전체 아이템 창 정보 다 가져옴 armour , offHand Whatever
        Optional.ofNullable(this.playerInventory.getHelmet()).ifPresent(
                helmet -> {
                    if (helmet.getType() == TOTEM)
                        this.playerInventory.setHelmet(new ItemStack(Material.AIR));
                });

        Optional.of(this.playerInventory.getItemInOffHand()).ifPresent(
                offhand -> {
                    if (offhand.getType() == TOTEM)
                        this.playerInventory.setItemInOffHand(new ItemStack(Material.AIR));
                    else
                        this.playerInventory.addItem(this.playerInventory.getItemInOffHand());
                });
    }

    private boolean isNotAllowedThisEvent() {
        long maxCnt = this.totemCountData.stream()
                .filter(cnt -> cnt >= MINIMUM && cnt < STACK)
                .count();

        long minCnt = this.totemCountData.stream()
                .filter(cnt -> cnt == MINIMUM)
                .count();

        String message = "";
        ItemStack playerOffHandItem = this.playerInventory.getItemInOffHand();
        if (this.totemCountData.isEmpty())
            message = "인벤토리에 토템이 없습니다.";

        else if (playerOffHandItem.getType() == TOTEM)
            if (playerOffHandItem.getAmount() == 64)
                message = "토템은 최대 한 세트만 합칠 수 있습니다.";

        else if (maxCnt < 2 && minCnt < 1)
            message = "합칠 토템이 없습니다.";

        else if (maxCnt < 2 && minCnt < 2)
            message = "2개 이상의 토템을 가지고 있으셔야 합니다.";


        if (message.isEmpty())
            return true;

        playerSendMsgComponentExchanger(this.player, message, ColorType.RED);
        return false;
    }
}