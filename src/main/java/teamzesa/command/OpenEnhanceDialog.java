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
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.DataBase.UserHandler.MenuGenerator.SlotItemMapping;
import teamzesa.DataBase.enhance.EnhanceInventoryManager;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.UI.InventoryCustomModelIds;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.event.Enhance.PlayerInteraction.UserInterface.EnhanceUIClickEvent;
import teamzesa.util.Interface.UserUIGenerator.InventoryUIGenerator;
import teamzesa.Enum.ListOfCommand;

import java.util.List;


public class OpenEnhanceDialog extends CommandRegisterSection {

    public OpenEnhanceDialog() {
        super(ListOfCommand.ENHANCE);
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
        InventoryCustomModelIds panelModelData = InventoryCustomModelIds.PANEL_STUFF_CUSTOM_DATA;
        switch (material) {
            case Material.RED_STAINED_GLASS_PANE -> panelModelData = InventoryCustomModelIds.EXECUTE_STUFF_DATA;
            case Material.LIGHT_BLUE_STAINED_GLASS_PANE -> panelModelData = InventoryCustomModelIds.EXECUTE_DISCORD_DATA;
            case Material.LIGHT_GRAY_STAINED_GLASS_PANE -> panelModelData = InventoryCustomModelIds.EXECUTE_NOTION_DATA;
        }

        meta.setCustomModelData(panelModelData.getValues());
        item.setItemMeta(meta);
        return item;
    }

    /**
     * refactoring
     * 해당 순서대로 Inventory index가 설정됩니다.
     * 0 1 2 - 🔪📜📜
     * 3 4 5
     * 6 7 8 - 🟦🟥◻️
     *
     * 0 - 강화용 아이템을 넣는 가이드 무기를 표기합니다.
     * 1 - 강화 주문서를 넣는 가이드를 표기 합니다.
     * 2 - 파괴방지 주문서를 넣는 가이드를 표기 합니다.
     *
     * 6 - 디스코드 링크를 표기합니다.
     * 7 - 강화를 실행합니다. {@link EnhanceUIClickEvent} 를 참고해주세요.
     * 8 - 노션링크를 표기합니다.
     * 웹 사이트 링크는 {@link ConfigIOHandler} 를 참고하면 됩니다.
     *
     * @return {@link Inventory}
     */
    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;

        ItemStack targetStuff = createItem(Material.NETHERITE_SWORD, "강화할 아래슬롯에 무기를 올려주세요", ColorList.ORANGE);
        ItemStack scrollStuff = createItem(Material.ANVIL, "아이템에 들어갈 재료를 아래슬롯에 넣어주세요", ColorList.ORANGE);
        ItemStack protectScrollStuff = createItem(Material.HEART_OF_THE_SEA, "파괴방어 스크롤을 아래슬롯에 넣어주세요", ColorList.ORANGE);
        ItemStack discordButton = createExecuteItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "디스코드", ColorList.DISCORD_COLOR);
        ItemStack executeButton = createExecuteItem(Material.RED_STAINED_GLASS_PANE, "강화 실행", ColorList.RED);
        ItemStack notionButton = createExecuteItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "강화법 확인하기", ColorList.NOTION_COLOR);

        Inventory enhanceUI = new InventoryUIGenerator()
                .chestOwner(player)
                .setInventory(InventoryType.DROPPER, componentExchanger("강화", ColorList.RED))
                .setEnhanceUIItem(List.of(
                        new SlotItemMapping(0, targetStuff),
                        new SlotItemMapping(1, scrollStuff),
                        new SlotItemMapping(2, protectScrollStuff),
                        new SlotItemMapping(6, discordButton),
                        new SlotItemMapping(7, executeButton),
                        new SlotItemMapping(8, notionButton)
                ))
                .executeUI();

        EnhanceInventoryManager.getEnhanceInventoryManager()
                .insert(player.getUniqueId(), enhanceUI);
        return true;
    }
}
