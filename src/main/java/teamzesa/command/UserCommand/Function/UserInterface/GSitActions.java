package teamzesa.command.UserCommand.Function.UserInterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.PlayerInteraction.UserInterface.SlotItemMapping;
import teamzesa.Util.UserUIGenerator.InventoryUIGenerator;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

import java.util.List;

public class GSitActions extends CommandRegisterSection {

    public GSitActions() {
        super(ListOfCommand.GSIT_ACTIONS);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;

        /**
         * 총 4개의 매뉴, 각 메뉴별로 아이템으로 표기예정
         *⬜️🟧⬜️🟧⬜️🟧⬜️🟧⬜️
         * 0 1 2 3 4  5 6 7 8
         */

        Inventory uis = new InventoryUIGenerator()
                .chestOwner(player)
                .setInventory(
                        9,
                        componentExchanger("포즈메뉴", ColorType.ORANGE)
                )
                .setEnhanceUIItem(List.of(
                        new SlotItemMapping(0, new ItemStack(Material.PAPER)),
                        new SlotItemMapping(1, new ItemStack(Material.PAPER)),
                        new SlotItemMapping(2, new ItemStack(Material.PAPER)),
                        new SlotItemMapping(3, new ItemStack(Material.PAPER))
                ))
                .executeUI();

        return true;
    }
}
