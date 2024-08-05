package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;


public class EnhanceDialog extends CommandRegisterSection {
    public static Inventory ENHANCE_DIALOG;

    private Player sendPlayer;
    private ItemStack targetStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;
    private ItemStack executeButton;
    private ItemStack notionButton;
    private ItemStack discordButton;

    public final static int PANEL_STUFF_CUSTOM_DATA = 20000;
    public final static int EXECUTE_STUFF_DATA = 30000;
    public final static int EXECUTE_DISCORD_DATA = 40000;
    public final static int EXECUTE_NOTION_DATA = 50000;

    public EnhanceDialog() {
        super(CommandExecutorMap.ENHANCE);
        init();
    }

    private void init() {
//        panel
        this.targetStuff = createItem(Material.NETHERITE_SWORD, "강화할 아래슬롯에 무기를 올려주세요", ColorMap.ORANGE);
        this.scrollStuff = createItem(Material.ANVIL, "아이템에 들어갈 재료를 아래슬롯에 넣어주세요", ColorMap.ORANGE);
        this.protectScrollStuff = createItem(Material.HEART_OF_THE_SEA, "파괴방어 스크롤을 아래슬롯에 넣어주세요", ColorMap.ORANGE);
        this.executeButton = createExecuteItem(Material.RED_STAINED_GLASS_PANE, "강화 실행", ColorMap.RED);
        this.discordButton = createExecuteItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE,"디스코드", ColorMap.DISCORD_COLOR);
        this.notionButton = createExecuteItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE,"강화법 확인하기", ColorMap.NOTION_COLOR);
    }

    private ItemStack createExecuteItem(Material material, String comment, ColorMap color) {
        return createItem(material, comment, color);
    }

    private ItemStack createItem(Material material, String comment, ColorMap color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(componentExchanger(comment, color));

        meta.addEnchant(Enchantment.CHANNELING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        // 강화창 디스플레이 아이템코드 또는 강화 실행 아이템코드 설정
        int panelModelData = 0;
        switch (material) {
            case Material.RED_STAINED_GLASS_PANE        -> panelModelData = EXECUTE_STUFF_DATA;
            case Material.LIGHT_BLUE_STAINED_GLASS_PANE -> panelModelData = EXECUTE_DISCORD_DATA;
            case Material.LIGHT_GRAY_STAINED_GLASS_PANE -> panelModelData = EXECUTE_NOTION_DATA;
            default                                     -> panelModelData = PANEL_STUFF_CUSTOM_DATA;
        }

        meta.setCustomModelData(panelModelData);

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        this.sendPlayer = (Player) commandSender;
        ENHANCE_DIALOG = Bukkit.createInventory(sendPlayer, InventoryType.DROPPER, componentExchanger("강화", ColorMap.RED));

        /*
        0 1 2
        3 4 5
        6 7 8
        */

//        🔪📜📜
        ENHANCE_DIALOG.setItem(0, this.targetStuff);
        ENHANCE_DIALOG.setItem(1, this.scrollStuff);
        ENHANCE_DIALOG.setItem(2, this.protectScrollStuff);

//        🟦🟥◻️
        ENHANCE_DIALOG.setItem(6, this.discordButton);
//        ENHANCE_DIALOG.setItem(6, this.executeButton);
        ENHANCE_DIALOG.setItem(7, this.executeButton);
//        ENHANCE_DIALOG.setItem(8, this.executeButton);
        ENHANCE_DIALOG.setItem(8, this.notionButton);

        sendPlayer.openInventory(ENHANCE_DIALOG);
        return true;
    }
}
