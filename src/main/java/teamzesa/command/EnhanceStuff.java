package teamzesa.command;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandExecutorMap;

import java.awt.*;
import java.util.ArrayList;

public class EnhanceStuff extends CommandRegisterSection {
    private Player sendPlayer;
    private ItemStack spaceStuff;
    private ItemStack weaponStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;
    private ItemStack executeButton;
    public final static int PANEL_STUFF_CUSTOM_DATA = 20000;
    public final static int EXECUTE_STUFF_DATA = 30000;

    public EnhanceStuff() {
        super(CommandExecutorMap.ENHANCE);
        init();
    }

    private void init() {
//        panel
        this.spaceStuff         = createItem(Material.GRAY_STAINED_GLASS_PANE, "X",                                     ColorList.RED);
        this.weaponStuff        = createItem(Material.NETHERITE_SWORD,         "강화할 아래슬롯에 무기를 올려주세요",         ColorList.ORANGE);
        this.scrollStuff        = createItem(Material.ANVIL,                   "아이템에 들어갈 재료를 아래슬롯에 넣어주세요",  ColorList.ORANGE);
        this.protectScrollStuff = createItem(Material.HEART_OF_THE_SEA,        "파괴방어 스크롤을 아래슬롯에 넣어주세요",      ColorList.ORANGE);
        this.executeButton      = createItem(Material.RED_STAINED_GLASS_PANE,  "강화 실행",                               ColorList.DISCORD_COLOR);
        this.executeButton.getItemMeta().setCustomModelData(EXECUTE_STUFF_DATA);
    }

    private ItemStack createItem(Material material, String comment, ColorList color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(componentExchanger(comment, color));

        meta.addEnchant(Enchantment.CHANNELING, 1 ,true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setCustomModelData(PANEL_STUFF_CUSTOM_DATA); // 강화창 디스플레이 아이템코드

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        this.sendPlayer = (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(sendPlayer, 2 * 9, componentExchanger("강화", ColorList.RED));

        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, this.spaceStuff);

        //4,13,22,31
//        🔪📜📜
        inventory.setItem(3, this.weaponStuff);
        inventory.setItem(4, this.scrollStuff);
        inventory.setItem(5, this.protectScrollStuff);
//
////        ⬜️⬜️⬜️
        inventory.setItem(12, null);
        inventory.setItem(13, null);
        inventory.setItem(14, null);

        inventory.setItem(17, this.executeButton);

        sendPlayer.openInventory(inventory);
        return true;
    }
}
