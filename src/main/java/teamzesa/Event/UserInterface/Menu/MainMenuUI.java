package teamzesa.Event.UserInterface.Menu;

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
import teamzesa.Event.UserInterface.Function.UIGenerator.InventoryUIGenerator;
import teamzesa.Event.UserInterface.Function.Interface.Type;
import teamzesa.Event.UserInterface.Function.Interface.UIType;
import teamzesa.Util.Function.StringComponentExchanger;
import teamzesa.Event.UserInterface.Function.UIGenerator.SlotItemMapping;
import teamzesa.Event.UserInterface.Function.Interface.UIHolder;

import java.util.ArrayList;

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
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        45,
                        componentExchanger("서버메뉴", ColorType.RED)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ItemStack panel = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemStack item = new ItemStack(Material.PAPER);
        ItemStack item1 = new ItemStack(Material.DIAMOND);

        ItemStack emerald = new ItemStack(Material.EMERALD);

        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta data = (SkullMeta) headItemStack.getItemMeta();
        data.setOwningPlayer(this.chestOwner);
        headItemStack.setItemMeta(data);


        ArrayList<SlotItemMapping> result = new ArrayList<>();
        for (int i = 0; i < 45; i++)
            result.add(new SlotItemMapping(i, panel));

        result.add(new SlotItemMapping(0, item));
        result.add(new SlotItemMapping(1, item));
        result.add(new SlotItemMapping(9, item));
        result.add(new SlotItemMapping(10, item));

        result.add(new SlotItemMapping(3, item));
        result.add(new SlotItemMapping(4, item));
        result.add(new SlotItemMapping(5, item));
        result.add(new SlotItemMapping(12, item));
        result.add(new SlotItemMapping(13, item));
        result.add(new SlotItemMapping(14, item));

        result.add(new SlotItemMapping(7, item));
        result.add(new SlotItemMapping(8, item));
        result.add(new SlotItemMapping(16, item));
        result.add(new SlotItemMapping(17, item));


        result.add(new SlotItemMapping(18, item));
        result.add(new SlotItemMapping(19, item));
        result.add(new SlotItemMapping(27, item));
        result.add(new SlotItemMapping(28, item));

        result.add(new SlotItemMapping(21, item));
        result.add(new SlotItemMapping(22, item));
        result.add(new SlotItemMapping(23, item));
        result.add(new SlotItemMapping(30, item));
        result.add(new SlotItemMapping(31, item));
        result.add(new SlotItemMapping(32, item));

        result.add(new SlotItemMapping(25, item));
        result.add(new SlotItemMapping(26, item));
        result.add(new SlotItemMapping(34, item));
        result.add(new SlotItemMapping(35, item));


        result.add(new SlotItemMapping(36, emerald));
        result.add(new SlotItemMapping(44, headItemStack));

        return result;
    }
}