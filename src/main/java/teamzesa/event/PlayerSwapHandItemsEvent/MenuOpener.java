package teamzesa.event.PlayerSwapHandItemsEvent;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamzesa.DataBase.UserHandler.MenuGenerator.SlotItemMapping;
import teamzesa.DataBase.UserHandler.MenuGenerator.UserInventoryManager;
import teamzesa.Enum.ColorList;
import teamzesa.event.EventRegister.EventRegister;
import teamzesa.util.Interface.StringComponentExchanger;
import teamzesa.util.Interface.UserUIGenerator.InventoryUIGenerator;

import java.util.List;

public class MenuOpener extends StringComponentExchanger implements EventRegister {

    private Player chestOwner;

    private final PlayerSwapHandItemsEvent event;

    public MenuOpener(PlayerSwapHandItemsEvent event) {
        this.event = event;
        this.chestOwner = this.event.getPlayer();

        if (BooleanUtils.isFalse(this.chestOwner.isSneaking()))
            return;

        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {

        ItemStack item1 = new ItemStack(Material.PAPER);
        ItemStack item2 = new ItemStack(Material.PAPER);
        ItemStack item3 = new ItemStack(Material.PAPER);
        ItemStack item4 = new ItemStack(Material.PAPER);
        ItemStack item5 = new ItemStack(Material.PAPER);
        ItemStack item6 = new ItemStack(Material.PAPER);
        ItemStack item7 = new ItemStack(Material.PAPER);
        ItemStack item8 = new ItemStack(Material.PAPER);

        Inventory mainMenu = new InventoryUIGenerator()
                .chestOwner(this.chestOwner)
                .setInventory(54, componentExchanger("강화", ColorList.RED))
                .setEnhanceUIItem(List.of(
                        new SlotItemMapping(0, item1),
                        new SlotItemMapping(2, item2),
                        new SlotItemMapping(5, item3),
                        new SlotItemMapping(7, item4),
                        new SlotItemMapping(18, item5),
                        new SlotItemMapping(20, item6),
                        new SlotItemMapping(23, item7),
                        new SlotItemMapping(25, item8)
                ))
                .executeUI();

        UserInventoryManager.getUserInventoryManager()
                .insert(this.chestOwner.getUniqueId(), mainMenu);
    }
}
