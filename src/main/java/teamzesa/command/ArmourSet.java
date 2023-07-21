package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import teamzesa.ComponentExchanger;

import java.awt.*;

public class ArmourSet extends ComponentExchanger implements CommandExecutor {
    private Player player;
    private PlayerInventory playerInventory;

    public enum ArmourType {
        헬멧
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.player = (Player)sender;
        this.playerInventory = player.getInventory();

        ItemStack itemInHand = playerInventory.getItemInMainHand();
        if (itemInHand == null) {
            playerAnnouncer(this.player,"손에 아이템이 없습니다.", Color.RED);
            return false;
        }

        ArmourType armourType = ArmourType.valueOf(label);
        switch (armourType) {
            case 헬멧 -> headSet(itemInHand);
        }
        return true;
    }

    private void headSet(ItemStack itemInHand){
        ItemStack armourHead = playerInventory.getHelmet();
        playerInventory.setHelmet(itemInHand);
        playerInventory.setItemInMainHand(armourHead);
    }
}
