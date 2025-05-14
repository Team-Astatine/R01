package teamzesa.Event.UserInterface.TPA;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import teamzesa.Enumeration.Type.ColorType;
import teamzesa.Event.UserInterface.Function.Interface.Type;
import teamzesa.Event.UserInterface.Function.Interface.UIHolder;
import teamzesa.Event.UserInterface.Function.Interface.UIType;
import teamzesa.Event.UserInterface.Function.UIGenerator.CreatePanelItem;
import teamzesa.Event.UserInterface.Function.UIGenerator.InventoryUIGenerator;
import teamzesa.Event.UserInterface.Function.UIGenerator.SlotItemMapping;
import teamzesa.Util.Function.StringComponentExchanger;

import java.util.ArrayList;
import java.util.List;

@UIType(Type.TPA)
public class TpaUI extends StringComponentExchanger implements UIHolder {
    private Player chestOwner;
    private Inventory inventory;
    private int slotCount;

    public TpaUI(Player player) {
        this.chestOwner = player;
        this.slotCount = 9;
        UIExecutor();
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
    public void UIExecutor() {
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("TPA 요청", ColorType.GREEN)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();

//
//        onlinePlayers.size() % 9 =
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>();
        for (int i = 0; i < this.slotCount; i++)
            result.add(
                    new SlotItemMapping(i, createItem(
                            new ItemStack(Material.WHITE_STAINED_GLASS_PANE),
                            "",
                            ColorType.WHITE
                    ))
            );

        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (int i = 0; i < onlinePlayers.size(); i++) {
            result.add(
                    new SlotItemMapping(i, createItem(
                            createHead(onlinePlayers.get(i).getPlayer()),
                            onlinePlayers.get(i).getName(),
                            ColorType.COMMAND_COLOR
                    ))
            );
        }

        return result;
    }

    private ItemStack createHead(Player player) {
        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta data = (SkullMeta) headItemStack.getItemMeta();

        data.setOwningPlayer(player);
        headItemStack.setItemMeta(data);

        return headItemStack;
    }

    private ItemStack createItem(ItemStack itemStack, String comment, ColorType color) {
        return new CreatePanelItem()
                .setPanelItem(itemStack)
                .setDisplayName(comment, color)
                .isEnchantGlowing(false)
                .createItem();
    }
}
