package teamzesa.Event.UserInterface.TPA;

import net.kyori.adventure.text.Component;
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
import java.util.Arrays;
import java.util.List;

@UIType(Type.TPA)
public class TpaUI extends StringComponentExchanger implements UIHolder {
    private final int MINIUM_TAB_CNT = 9;

    private Player chestOwner;
    private Inventory inventory;

    private List<Player> onlinePlayers;
    private int slotCount;

    public TpaUI(Player player) {
        this.chestOwner = player;
        setOnlinePlayer();
        calculatingSlotCount();
        UIExecutor();
    }

    private void setOnlinePlayer() {
        this.onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
//        Exclude the Chest Owner from the oneline player list
        this.onlinePlayers.removeIf(
                targetPlayer -> targetPlayer.getName().equals(this.chestOwner.getName())
        );
    }

    @Override
    public Player getOwner() {
        return this.chestOwner;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    private void calculatingSlotCount() {
        int playerCount = this.onlinePlayers.size();

        int fullPages = playerCount / MINIUM_TAB_CNT;
        boolean hasRemainder = (playerCount % MINIUM_TAB_CNT) > 0;
        int line = fullPages + (hasRemainder ? 1 : 0);

        if (line == 0)
            line = 1;

        this.slotCount = line * MINIUM_TAB_CNT;
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
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>();
        for (int i = 0; i < this.slotCount; i++)
            result.add(
                    new SlotItemMapping(i, createItem(
                            new ItemStack(Material.WHITE_STAINED_GLASS_PANE),
                            "",
                            ColorType.WHITE,
                            null
                    ))
            );

        for (int i = 0; i < this.onlinePlayers.size(); i++) {
            result.add(
                    new SlotItemMapping(i, createItem(
                            createHead(this.onlinePlayers.get(i).getPlayer()),
                            this.onlinePlayers.get(i).getName(),
                            ColorType.COMMAND_COLOR,
                            new ArrayList<>(Arrays.asList(
                                    componentExchanger("왼쪽 클릭 : TPA 요청", ColorType.YELLOW),
                                    componentExchanger("오른쪽 클릭 : TPA HERE 요청", ColorType.YELLOW)
                            ))
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

    private ItemStack createItem(ItemStack itemStack, String comment, ColorType color, ArrayList<Component> lore) {
        return new CreatePanelItem()
                .setPanelItem(itemStack)
                .setDisplayName(comment, color)
                .setLore(lore)
                .isEnchantGlowing(false)
                .createItem();
    }
}
