package teamzesa.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import teamzesa.ComponentExchanger;

public class Meteor extends ComponentExchanger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 3) {
            sender.sendMessage("Please provide x, y and z coordinates.");
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

        ArmorStand meteorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        meteorStand.setHelmet(meteorItem);
        meteorStand.setVisible(false); // Make the armor stand invisible, so only the item is seen
        meteorStand.setGravity(true);
        meteorStand.setInvulnerable(true);
        meteorStand.setCustomName("Meteor");
        meteorStand.setCustomNameVisible(false);

        return true;
    }
}