package teamzesa.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.DataBase.IOHandler.ConfigIOHandler;
import teamzesa.DataBase.UserHandler.MenuGenerator.SlotItemMapping;
import teamzesa.DataBase.UserInterface.PanelItem;
import teamzesa.DataBase.enhance.EnhanceInventoryManager;
import teamzesa.Enum.ColorList;
import teamzesa.Enum.Enhance.UI.InventoryCustomModelIds;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.event.Enhance.PlayerInteraction.UserInterface.EnhanceUIClickEvent;
import teamzesa.util.Interface.UserUIGenerator.CreatePanelItem;
import teamzesa.util.Interface.UserUIGenerator.InventoryUIGenerator;
import teamzesa.Enum.ListOfCommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpenEnhanceDialog extends CommandRegisterSection {
    // 슬롯 인덱스 상수 정의
    private final int SLOT_WEAPON = 0;
    private final int SLOT_SCROLL = 1;
    private final int SLOT_PROTECT = 2;
    private final int SLOT_DISCORD = 6;
    private final int SLOT_EXECUTE = 7;
    private final int SLOT_NOTION = 8;

    public OpenEnhanceDialog() {
        super(ListOfCommand.ENHANCE);
    }

    /**
     * 인벤토리 UI 설정
     *
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
     */
    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;

        Inventory enhanceUI = new InventoryUIGenerator()
                .chestOwner(player)
                .setInventory(InventoryType.DROPPER, componentExchanger("강화", ColorList.RED))
                .setEnhanceUIItem(generateItemMappings())
                .executeUI();

        EnhanceInventoryManager.getEnhanceInventoryManager()
                .insert(player.getUniqueId(), enhanceUI);
        return true;
    }

    /**
     * 슬롯에 배치할 아이템 매핑을 생성합니다.
     *
     * @return 아이템 매핑 목록
     */
    private List<SlotItemMapping> generateItemMappings() {
        return Arrays.asList(
                new SlotItemMapping(SLOT_WEAPON, createItem(
                        Material.NETHERITE_SWORD,
                        InventoryCustomModelIds.PANEL_STUFF_CUSTOM_DATA,
                        "강화할 아래슬롯에 무기를 올려주세요",
                        ColorList.ORANGE)),

                new SlotItemMapping(SLOT_SCROLL, createItem(
                        Material.ANVIL,
                        InventoryCustomModelIds.PANEL_STUFF_CUSTOM_DATA,
                        "아이템에 들어갈 재료를 아래슬롯에 넣어주세요",
                        ColorList.ORANGE)),

                new SlotItemMapping(SLOT_PROTECT, createItem(
                        Material.HEART_OF_THE_SEA,
                        InventoryCustomModelIds.PANEL_STUFF_CUSTOM_DATA,
                        "파괴방어 스크롤을 아래슬롯에 넣어주세요",
                        ColorList.ORANGE)),

                new SlotItemMapping(SLOT_DISCORD, createItem(
                        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                        InventoryCustomModelIds.EXECUTE_DISCORD_DATA,
                        "디스코드 링크받기",
                        ColorList.DISCORD_COLOR)),

                new SlotItemMapping(SLOT_EXECUTE, createItem(
                        Material.RED_STAINED_GLASS_PANE,
                        InventoryCustomModelIds.EXECUTE_STUFF_DATA,
                        "강화 실행",
                        ColorList.RED)),

                new SlotItemMapping(SLOT_NOTION, createItem(
                        Material.LIGHT_GRAY_STAINED_GLASS_PANE,
                        InventoryCustomModelIds.EXECUTE_NOTION_DATA,
                        "강화법 확인하기",
                        ColorList.NOTION_COLOR))
        );
    }

    /**
     * 기본 아이템 생성 메서드
     */
    private ItemStack createItem(Material material, InventoryCustomModelIds modelId, String comment, ColorList color) {
        return new CreatePanelItem()
                .setPanelItem(new PanelItem(material, modelId.getValues()))
                .setComment(comment)
                .setColor(color)
                .createItem();
    }
}
