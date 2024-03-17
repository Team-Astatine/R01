package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.util.Enum.ColorList;
import teamzesa.util.Enum.CommandExecutorMap;

public class EnhanceStuff extends CommandRegisterSection {
    private Player sendPlayer;
    private ItemStack spaceStuff;
    private ItemStack weaponStuff;
    private ItemStack enchantStuff;
    private ItemStack coverScrollStuff;


    public EnhanceStuff() {
        super(CommandExecutorMap.ENHANCE);
        init();
    }

    private void init() {
        this.spaceStuff = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        this.sendPlayer = (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(sendPlayer , 4 * 9, componentExchanger("강화"));

        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, this.spaceStuff);

        //4,13,22,31
        inventory.setItem(13, new ItemStack(Material.GRAY_BANNER));
        inventory.setItem(22, new ItemStack(Material.GRAY_BANNER));

        sendPlayer.openInventory(inventory);
        return true;
    }
}
