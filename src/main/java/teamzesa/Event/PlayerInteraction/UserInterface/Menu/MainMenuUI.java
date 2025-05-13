package teamzesa.Event.PlayerInteraction.UserInterface.Menu;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.EventRegister;
import teamzesa.Util.UserUIGenerator.UIGenerator.InventoryUIGenerator;
import teamzesa.Util.UserUIGenerator.Interface.Type;
import teamzesa.Util.UserUIGenerator.Interface.UIType;
import teamzesa.Util.Function.StringComponentExchanger;
import teamzesa.Util.UserUIGenerator.UIGenerator.SlotItemMapping;
import teamzesa.Util.UserUIGenerator.Interface.UIHolder;

import java.util.List;

@UIType(Type.MAIN_MENU)
public class MainMenuUI extends StringComponentExchanger implements EventRegister, UIHolder {
    private Player chestOwner;
    private Inventory inventory;

    private final PlayerSwapHandItemsEvent event;

    public MainMenuUI(PlayerSwapHandItemsEvent event) {
        this.event = event;
        this.chestOwner = this.event.getPlayer();

        if (BooleanUtils.isFalse(this.chestOwner.isSneaking()))
            return;

        this.event.setCancelled(true);
        init();
        execute();
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
    public void init() {}

    @Override
    public void execute() {
        this.inventory = createMainMenuInventory();
    }

    private Inventory createMainMenuInventory() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemStack item1 = new ItemStack(Material.DIAMOND);

        ItemStack emerald = new ItemStack(Material.EMERALD);

        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta data = (SkullMeta) headItemStack.getItemMeta();
        data.setOwningPlayer(this.chestOwner);
        headItemStack.setItemMeta(data);

        return new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                    45,
                    componentExchanger("서버메뉴", ColorType.RED)
                )
                .setEnhanceUIItem(
                    List.of(
//                                First Line
                        new SlotItemMapping(0, item),
                        new SlotItemMapping(1, item),
                        new SlotItemMapping(9, item),
                        new SlotItemMapping(10, item),

                        new SlotItemMapping(3, item),
                        new SlotItemMapping(4, item),
                        new SlotItemMapping(5, item),
                        new SlotItemMapping(12, item),
                        new SlotItemMapping(13, item),
                        new SlotItemMapping(14, item),

                        new SlotItemMapping(7, item),
                        new SlotItemMapping(8, item),
                        new SlotItemMapping(16, item),
                        new SlotItemMapping(17, item),

//                                Second Line
                        new SlotItemMapping(18, item1),
                        new SlotItemMapping(19, item1),
                        new SlotItemMapping(27, item1),
                        new SlotItemMapping(28, item1),

                        new SlotItemMapping(21, item1),
                        new SlotItemMapping(22, item1),
                        new SlotItemMapping(23, item1),
                        new SlotItemMapping(30, item1),
                        new SlotItemMapping(31, item1),
                        new SlotItemMapping(32, item1),

                        new SlotItemMapping(25, item1),
                        new SlotItemMapping(26, item1),
                        new SlotItemMapping(34, item1),
                        new SlotItemMapping(35, item1),

//                                Third Line
                        new SlotItemMapping(36, emerald),
                        new SlotItemMapping(44, headItemStack)
                ))
                .executeUI();
    }
}