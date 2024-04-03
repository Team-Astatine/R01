package teamzesa.command;

import net.kyori.adventure.text.Component;
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
        this.weaponStuff = createItem(Material.NETHERITE_SWORD, "강화할 아래슬롯에 무기를 올려주세요", ColorList.ORANGE);
        this.scrollStuff = createItem(Material.ANVIL, "아이템에 들어갈 재료를 아래슬롯에 넣어주세요", ColorList.ORANGE);
        this.protectScrollStuff = createItem(Material.HEART_OF_THE_SEA, "파괴방어 스크롤을 아래슬롯에 넣어주세요", ColorList.ORANGE);
        this.executeButton = createExecuteItem(Material.RED_STAINED_GLASS_PANE, "강화 실행", ColorList.DISCORD_COLOR);
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
        meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        // 강화창 디스플레이 아이템코드 또는 강화 실행 아이템코드 설정
        meta.setCustomModelData(material == Material.RED_STAINED_GLASS_PANE ? EXECUTE_STUFF_DATA : PANEL_STUFF_CUSTOM_DATA);

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        this.sendPlayer = (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(sendPlayer, InventoryType.DROPPER, componentExchanger("강화", ColorList.RED));

//        //4,13,22,31
//        🔪📜📜
        inventory.setItem(0, this.weaponStuff);
        inventory.setItem(1, this.scrollStuff);
        inventory.setItem(2, this.protectScrollStuff);

        for (int i = 6; i < 9; i++)
            inventory.setItem(i, this.executeButton);

        sendPlayer.openInventory(inventory);
        return true;
    }
}
