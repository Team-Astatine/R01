package teamzesa.command.UserCommand.Function;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.CommandRegisterSection;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.command.ListOfCommand;

public class Hat extends CommandRegisterSection {
    private Player player;
    private PlayerInventory playerInventory;

    public Hat() {
        super(ListOfCommand.ARMOUR_HEAD);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        this.player = (Player) sender;
        this.playerInventory = this.player.getInventory();

        ItemStack tmpItemInHand = this.playerInventory.getItemInMainHand();
        if (tmpItemInHand.isEmpty()) {
            playerSendMsgComponentExchanger(this.player, "손에 아이템이 없습니다.", ColorType.RED);
            return false;
        }

        ItemStack armourHead = this.playerInventory.getHelmet();
        this.playerInventory.setHelmet(tmpItemInHand);
        this.playerInventory.setItemInMainHand(armourHead);
        playerSendMsgComponentExchanger(this.player, "머리에 썼어요!", ColorType.YELLOW);

        return true;
    }
}