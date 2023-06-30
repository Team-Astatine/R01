package teamzesa.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmourSet implements CommandExecutor {
    private Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.player = (Player)sender;

        if (label.equals("머리"))
            headSet();
        if (label.equals("몸통"))
            bodySet();
        if (label.equals("바지"))
            pantSet();
        if (label.equals("신발"))
            shooSet();

        else return false;
        return true;
    }

    private void headSet(){
        ItemStack armourHead = this.player.getInventory().getHelmet();
        ItemStack onCursor = this.player.getItemOnCursor();

        if (onCursor == null) {
            this.player.sendMessage(ChatColor.RED + "손에 아이템이 없습니다.");
            return;
        }

        this.player.getInventory().setHelmet(onCursor);
        player.setItemOnCursor(armourHead);
    }

    private void bodySet(){
        ItemStack armourHead = this.player.getInventory().getChestplate();
        ItemStack onCursor = this.player.getItemOnCursor();

        if (onCursor == null) {
            this.player.sendMessage(ChatColor.RED + "손에 아이템이 없습니다.");
            return;
        }

        this.player.getInventory().setChestplate(onCursor);
        player.setItemOnCursor(armourHead);
    }

    private void pantSet(){
        ItemStack armourHead = this.player.getInventory().getLeggings();
        ItemStack onCursor = this.player.getItemOnCursor();

        if (onCursor == null) {
            this.player.sendMessage(ChatColor.RED + "손에 아이템이 없습니다.");
            return;
        }

        this.player.getInventory().setLeggings(onCursor);
        player.setItemOnCursor(armourHead);
    }

    private void shooSet(){
        ItemStack armourHead = this.player.getInventory().getBoots();
        ItemStack onCursor = this.player.getItemOnCursor();

        if (onCursor == null) {
            this.player.sendMessage(ChatColor.RED + "손에 아이템이 없습니다.");
            return;
        }

        this.player.getInventory().setBoots(onCursor);
        player.setItemOnCursor(armourHead);
    }
}
