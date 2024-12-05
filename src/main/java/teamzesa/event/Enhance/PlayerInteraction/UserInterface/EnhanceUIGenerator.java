package teamzesa.event.Enhance.PlayerInteraction.UserInterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Interface.StringComponentExchanger;

import static teamzesa.DataBase.enhance.EnhanceInventoryHandler.*;

public class EnhanceUIGenerator extends StringComponentExchanger {
    public Inventory enhanceDialog;

    private Player sendPlayer;
    private ItemStack targetStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;
    private ItemStack executeButton;
    private ItemStack notionButton;
    private ItemStack discordButton;


    public EnhanceUIGenerator() {
    }

    public EnhanceUIGenerator setSendPlayer(Player sendPlayer) {
        this.sendPlayer = sendPlayer;
        return this;
    }

    public EnhanceUIGenerator generatingEnhanceUIItem() {
//        panel
        this.targetStuff = createItem(Material.NETHERITE_SWORD, "강화할 아래슬롯에 무기를 올려주세요", ColorList.ORANGE);
        this.scrollStuff = createItem(Material.ANVIL, "아이템에 들어갈 재료를 아래슬롯에 넣어주세요", ColorList.ORANGE);
        this.protectScrollStuff = createItem(Material.HEART_OF_THE_SEA, "파괴방어 스크롤을 아래슬롯에 넣어주세요", ColorList.ORANGE);
        this.executeButton = createExecuteItem(Material.RED_STAINED_GLASS_PANE, "강화 실행", ColorList.RED);
        this.discordButton = createExecuteItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "디스코드", ColorList.DISCORD_COLOR);
        this.notionButton = createExecuteItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "강화법 확인하기", ColorList.NOTION_COLOR);
        return this;
    }

//    refactoring
    public Inventory executeUI() {
        enhanceDialog = Bukkit.createInventory(sendPlayer, InventoryType.DROPPER, componentExchanger("강화", ColorList.RED));

        /*
        0 1 2
        3 4 5
        6 7 8
        */

//        🔪📜📜
        enhanceDialog.setItem(0, this.targetStuff);
        enhanceDialog.setItem(1, this.scrollStuff);
        enhanceDialog.setItem(2, this.protectScrollStuff);

//        🟦🟥◻️
        enhanceDialog.setItem(6, this.discordButton);
        enhanceDialog.setItem(7, this.executeButton);
        enhanceDialog.setItem(8, this.notionButton);

        return this.enhanceDialog;
    }

    private ItemStack createExecuteItem(Material material, String comment, ColorList color) {
        return createItem(material, comment, color);
    }

    private ItemStack createItem(Material material, String comment, ColorList color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(componentExchanger(comment, color));

        meta.addEnchant(Enchantment.CHANNELING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        // 강화창 디스플레이 아이템코드 또는 강화 실행 아이템코드 설정
        int panelModelData = 0;
        switch (material) {
            case Material.RED_STAINED_GLASS_PANE -> panelModelData = EXECUTE_STUFF_DATA;
            case Material.LIGHT_BLUE_STAINED_GLASS_PANE -> panelModelData = EXECUTE_DISCORD_DATA;
            case Material.LIGHT_GRAY_STAINED_GLASS_PANE -> panelModelData = EXECUTE_NOTION_DATA;
            default -> panelModelData = PANEL_STUFF_CUSTOM_DATA;
        }

        meta.setCustomModelData(panelModelData);
        item.setItemMeta(meta);
        return item;
    }
}
