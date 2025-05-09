package teamzesa.command.UserCommand.Function.UserInterface;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.PlayerInteraction.UserInterface.GSIT.GSitInventoryManager;
import teamzesa.Event.PlayerInteraction.UserInterface.InventoryUICustomModeData;
import teamzesa.Event.PlayerInteraction.UserInterface.PanelItem;
import teamzesa.Event.PlayerInteraction.UserInterface.SlotItemMapping;
import teamzesa.Util.UserUIGenerator.CreatePanelItem;
import teamzesa.Util.UserUIGenerator.InventoryUIGenerator;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

import java.util.Arrays;

public class GSitUI extends CommandRegisterSection {

    public GSitUI() {
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
         * ⬜️🟧⬜️🟧⬜️🟧⬜️🟧⬜️
         * 0 1  2 3 4 5  6 7 8
         */

        Inventory gsitUI = new InventoryUIGenerator()
                .chestOwner(player)
                .setInventory(
                        9,
                        componentExchanger("상호작용", ColorType.RED)
                )
                .setEnhanceUIItem(Arrays.asList(
                        new SlotItemMapping(1, createItem(
                                Material.BRICK_STAIRS,
                                InventoryUICustomModeData.SIT,
                                "앉기",
                                ColorType.ORANGE
                        )),
                        new SlotItemMapping(3, createItem(
                                Material.RED_BED,
                                InventoryUICustomModeData.LAY,
                                "눕기",
                                ColorType.ORANGE
                        )),
                        new SlotItemMapping(5, createItem(
                                Material.TRIDENT,
                                InventoryUICustomModeData.SPIN,
                                "돌기",
                                ColorType.ORANGE
                        )),
                        new SlotItemMapping(7, createItem(
                                Material.IRON_TRAPDOOR,
                                InventoryUICustomModeData.CRAWL,
                                "기어가기",
                                ColorType.ORANGE
                        ))
                ))
                .executeUI();

        GSitInventoryManager.getGSitInventoryManager()
                .insert(player.getUniqueId() ,gsitUI);

        return true;
    }

    private ItemStack createItem(Material material, InventoryUICustomModeData modelId, String comment, ColorType color) {
        return new CreatePanelItem()
                .setPanelItem(new PanelItem(material, modelId.getValues()))
                .setComment(comment)
                .setColor(color)
                .createItem();
    }
}
