package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;
import teamzesa.util.Enum.ColorList;

public class Hat extends CommandRegisterSection {
    private Player player;
    private PlayerInventory playerInventory;

    public Hat() {
        super(CommandExecutorMap.ARMOUR_HEAD);
    }

    private enum ArmourType {
        hat
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        this.player = (Player) sender;
        this.playerInventory = this.player.getInventory();

        ItemStack tmpItemInHand = this.playerInventory.getItemInMainHand();
        if (tmpItemInHand.isEmpty()) {
            playerSendMsgComponentExchanger(this.player,"손에 아이템이 없습니다.", ColorList.RED);
            return false;
        }

        ArmourType armourType = ArmourType.valueOf(label);
        switch (armourType) {
            case hat -> headSet(tmpItemInHand);
        }
        return true;
    }

    private void headSet(ItemStack temp){
        ItemStack armourHead = this.playerInventory.getHelmet();
        this.playerInventory.setHelmet(temp);
        this.playerInventory.setItemInMainHand(armourHead);
        playerSendMsgComponentExchanger(this.player,"머리에 썼어요!", ColorList.YELLOW);
    }
}
