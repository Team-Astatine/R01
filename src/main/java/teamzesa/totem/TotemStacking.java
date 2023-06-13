package teamzesa.totem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class TotemStacking implements CommandExecutor {
    private final int MINIMUM = 2;
    private Player p;
    private ItemStack offHandStuff;
    private ItemStack[] playerItemStack;
    private ArrayList<Integer> itemList;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        int totemCount = 0;
        p = (Player) sender;
        offHandStuff = p.getInventory().getItemInOffHand();
        playerItemStack = p.getInventory().getContents();
        itemList = new ArrayList<>();

//        Checking total Totem amount
        for (ItemStack item : playerItemStack) {
            if (item != null && item.getType() == Material.TOTEM_OF_UNDYING)
                itemList.add(item.getAmount());
        }

//        Exception Check
        if (itemList.size() < MINIMUM) {
            p.sendMessage(ChatColor.RED + "2개 이상의 토템을 가지고 있으셔야 합니다.");
            return false;
        }

//        remove Inventory
        for (ItemStack item : playerItemStack) {
            if (item != null && item.getType() == Material.TOTEM_OF_UNDYING) {
                p.getInventory().remove(item);
            }
        }
//        offHandCheck
        if (offHandStuff.getType() == Material.TOTEM_OF_UNDYING)
            p.getInventory().setItemInOffHand(null);

//        setTotem
        ItemStack stackOfTotem = new ItemStack(Material.TOTEM_OF_UNDYING,totalListCount(itemList));
        p.getInventory().addItem(stackOfTotem);
        p.sendRawMessage(ChatColor.YELLOW + "토템을 합쳤습니다.");

        return true;
    }

    public int totalListCount(ArrayList<Integer> list) {
        int result = 0;
        for (int i = 0; i < list.size(); i++)
            result += list.get(i);
        return result;
    }
}
