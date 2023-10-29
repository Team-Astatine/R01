package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;
import teamzesa.resgisterEvent.EventExecutor;

import java.awt.*;
import java.util.Optional;

public class ArmourSet implements CommandExecutor, EventExecutor {
    private PlayerInventory playerInventory;

    public enum ArmourType {
        머리
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        this.playerInventory = player.getInventory();

        Optional<ItemStack> tmpItemInHand = Optional.of(playerInventory.getItemInMainHand());
        if (tmpItemInHand.isEmpty()) {
            ComponentExchanger.playerAnnouncer(player,"손에 아이템이 없습니다.", Color.RED);
            return false;
        }

        ArmourType armourType = ArmourType.valueOf(label);
        switch (armourType) {
            case 머리 -> headSet(tmpItemInHand.get());
        }
        return true;
    }

    private void headSet(ItemStack temp){
        ItemStack armourHead = playerInventory.getHelmet();
        playerInventory.setHelmet(temp);
        playerInventory.setItemInMainHand(armourHead);
    }
}
