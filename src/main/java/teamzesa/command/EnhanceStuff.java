package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.CommandExecutorMap;

public class EnhanceStuff extends CommandRegisterSection {
    private Player sendPlayer;
    private ItemStack spaceStuff;
    private ItemStack weaponStuff;
    private ItemStack scrollStuff;
    private ItemStack protectScrollStuff;


    public EnhanceStuff() {
        super(CommandExecutorMap.ENHANCE);
        init();
    }

    private void init() {
//        panel
        this.spaceStuff = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        this.spaceStuff.setDisplayName("X");

//        weaponSpace
        this.weaponStuff = new ItemStack(Material.NETHERITE_SWORD);
        this.weaponStuff.setDisplayName("강화할 아래슬롯에 무기를 올려주세요");

//        scrollSpace
        this.scrollStuff = new ItemStack(Material.ANVIL);
        this.weaponStuff.setDisplayName("강화할 아래슬롯에 재료를 넣어주세요");

//        unbreakableScrollSpace
        this.protectScrollStuff = new ItemStack(Material.HEART_OF_THE_SEA);
        this.protectScrollStuff.setDisplayName("파괴방어 스크롤을 아래슬롯에 넣어주세요");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
//        methodimplement
//        InventoryType 구별 어떤 인벤토리일경우 해당 이벤트를 발생시킬지 확인
        this.sendPlayer = (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(sendPlayer , 4 * 9, componentExchanger("강화"));

        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, this.spaceStuff);

        //4,13,22,31
//        🔪📜📜
        inventory.setItem(12, this.weaponStuff);
        inventory.setItem(13, this.scrollStuff);
        inventory.setItem(14, this.protectScrollStuff);

//        ⬜️⬜️⬜️
        inventory.setItem(21, null);
        inventory.setItem(22, null);
        inventory.setItem(23, null);

        sendPlayer.openInventory(inventory);
        return true;
    }
}
