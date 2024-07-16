package teamzesa.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamzesa.command.register.CommandRegisterSection;
import teamzesa.event.Enhance.EnhanceUtil;
import teamzesa.util.Enum.ColorMap;
import teamzesa.util.Enum.CommandExecutorMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class EnhnaceSet extends CommandRegisterSection {

    private String comment;

    public EnhnaceSet() {
        super(CommandExecutorMap.ENHANCE_SET);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        if (!commandSender.isOp()) {
            playerSendMsgComponentExchanger(player,"해당 명령어는 플레이어가 사용할 수 없습니다.", ColorMap.RED);
            return Collections.emptyList();
        }

        int enhanceLevel = Integer.parseInt(strings[0]);
        if (enhanceLevel < 0 || enhanceLevel > 10) {
            playerSendMsgComponentExchanger(player,"0 ~ 10 사이 값만 대입 가능합니다.", ColorMap.RED);
            return Collections.emptyList();
        }

        ItemStack targetItem = player.getInventory().getItemInMainHand();

        ItemMeta targetItemMeta = targetItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        targetItem.setItemMeta(targetItemMeta);

        try {
            EnhanceUtil.addItemDescription(targetItem, enhanceLevel - targetItem.getItemMeta().getCustomModelData());
        } catch (Exception e) {
            e.printStackTrace();
        }

        playerSendMsgComponentExchanger(player, getComment(enhanceLevel) , ColorMap.GREEN);
        return new ArrayList<>(List.of("enhance"));
    }

    private String getComment(int enhanceLevel) {
        return switch (enhanceLevel) {
            case 0 -> "강화를 초기화 했습니다.";
            case 10 -> "최고 레벨로 강화했습니다.";
            default -> enhanceLevel + "강 으로 강화했습니다.";
        };
    }
}
