package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import teamzesa.util.ComponentExchanger;
import teamzesa.util.Enum.ColorList;
import teamzesa.event.EventExecutor;


public class Meteor extends ComponentExchanger implements CommandExecutor, EventExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return false;
        }

        if (args.length < 3) {
            playerSendMsgComponentExchanger((Player)sender, "x y z 좌표를 입력하세요.", ColorList.RED);
            return false;
        }

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);

        World world = Bukkit.getWorld("world"); // Assuming the default world name. Modify as needed.
        Location location = new Location(world, x, y, z);

        ItemStack meteorItem = new ItemStack(Material.PAPER);
        ItemMeta meta = meteorItem.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(5);
            meteorItem.setItemMeta(meta);
        }

        return true;
    }
}