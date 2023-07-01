package teamzesa.command;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmourSet implements CommandExecutor {
    private Player player;
    private PlayerInventory playerInventory;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.player = (Player)sender;
        this.playerInventory = player.getInventory();

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
        ItemStack armourHead = playerInventory.getHelmet();
        ItemStack itemInHand = playerInventory.getItemInMainHand();

        if (itemInHand == null) {
            this.player.sendPlainMessage(Color.RED + "손에 아이템이 없습니다.");
            return;
        }

        playerInventory.setHelmet(itemInHand);
        playerInventory.setItemInMainHand(armourHead);
    }

    private void bodySet(){
        ItemStack chestSet = playerInventory.getChestplate();
        ItemStack itemInHand = playerInventory.getItemInMainHand();

        if (itemInHand == null) {
            this.player.sendPlainMessage(Color.RED + "손에 아이템이 없습니다.");
            return;
        }

        playerInventory.setChestplate(itemInHand);
        playerInventory.setItemInMainHand(chestSet);
    }

    private void pantSet() {
        ItemStack pantSet = playerInventory.getLeggings();
        ItemStack itemInHand = playerInventory.getItemInMainHand();

        if (itemInHand == null) {
            this.player.sendPlainMessage(Color.RED + "손에 아이템이 없습니다.");
            return;
        }

        playerInventory.setLeggings(itemInHand);
        playerInventory.setItemInMainHand(pantSet);
    }

    private void shooSet() {
        ItemStack shooSet = playerInventory.getBoots();
        ItemStack itemInHand = playerInventory.getItemInMainHand();

        if (itemInHand == null) {
            this.player.sendPlainMessage(Color.RED + "손에 아이템이 없습니다.");
            return;
        }

        playerInventory.setBoots(itemInHand);
        playerInventory.setItemInMainHand(shooSet);
    }

}
