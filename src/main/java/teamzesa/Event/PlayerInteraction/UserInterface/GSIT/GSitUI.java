package teamzesa.Event.PlayerInteraction.UserInterface.GSIT;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Util.UserUIGenerator.Interface.UIHolder;
import teamzesa.Util.UserUIGenerator.UIGenerator.InventoryUIGenerator;
import teamzesa.Util.UserUIGenerator.Interface.Type;
import teamzesa.Util.UserUIGenerator.Interface.UIType;
import teamzesa.Util.UserUIGenerator.UIGenerator.SlotItemMapping;
import teamzesa.Util.UserUIGenerator.UIGenerator.CreatePanelItem;
import teamzesa.command.CommandRegisterSection;
import teamzesa.command.ListOfCommand;

import java.util.ArrayList;

@UIType(Type.GSIT)
public class GSitUI extends CommandRegisterSection implements UIHolder {
    private Player chestOwner;
    private Inventory inventory;

    public GSitUI() {
        super(ListOfCommand.GSIT_ACTIONS);
    }

    @Override
    public Player getOwner() {
        return this.chestOwner;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        this.chestOwner = (Player) commandSender;

        /**
         * 총 4개의 매뉴, 각 메뉴별로 아이템으로 표기예정
         * ⬜️🟧⬜️🟧⬜️🟧⬜️🟧⬜️
         * 0 1  2 3 4 5  6 7 8
         */

        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        9,
                        componentExchanger("상호작용", ColorType.RED)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();

        return true;
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ItemStack panel = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        ArrayList<SlotItemMapping> result = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            result.add(new SlotItemMapping(i, panel));

        result.add(new SlotItemMapping(
                1,
                createItem(
                Material.BRICK_STAIRS,
                "앉기",
                ColorType.ORANGE)
        ));

        result.add(new SlotItemMapping(
                3,
                createItem(
                Material.RED_BED,
                "눕기",
                ColorType.ORANGE
                )
        ));

        result.add(new SlotItemMapping(
                5,
                createItem(
                Material.TRIDENT,
                "돌기",
                ColorType.ORANGE
                )
        ));

        result.add(new SlotItemMapping(
                7,
                createItem(
                Material.IRON_TRAPDOOR,
                "기어가기",
                ColorType.ORANGE
        )
        ));

        return result;
    }

    private ItemStack createItem(Material material, String comment, ColorType color) {
        return new CreatePanelItem()
                .setPanelItem(material)
                .setComment(comment)
                .setColor(color)
                .createItem();
    }
}
