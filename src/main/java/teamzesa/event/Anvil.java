package teamzesa.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.purpurmc.purpur.event.inventory.AnvilUpdateResultEvent;

public class Anvil implements Listener {

    /*
    getRenameText(): 수리된 아이템에 적용될 이름을 가져옵니다. 빈 문자열은 기본 아이템 이름을 나타냅니다.

    getRepairCostAmount(): 현재 수리를 완료하는 데 필요한 아이템 비용(양)을 가져옵니다.

    setRepairCostAmount(int amount): 현재 수리를 완료하는 데 필요한 아이템 비용(양)을 설정합니다.

    getRepairCost(): 현재 수리를 완료하는 데 필요한 경험치 비용(레벨)을 가져옵니다.

    setRepairCost(int levels): 현재 수리를 완료하는 데 필요한 경험치 비용(레벨)을 설정합니다.

    getMaximumRepairCost(): 현재 수리에 의해 허용되는 최대 경험치 비용(레벨)을 가져옵니다. 만약 getRepairCost()의 결과가 반환된 값보다 크면 "너무 비싸다"로 인해 수리 결과가 공기로 설정됩니다.

    setMaximumRepairCost(int levels): 현재 수리에 의해 허용되는 최대 경험치 비용(레벨)을 설정합니다.

    getFirstItem(): 왼쪽 입력 슬롯에 있는 아이템을 가져옵니다.

    setFirstItem(ItemStack firstItem): 왼쪽 입력 슬롯에 아이템을 설정합니다.

    getSecondItem(): 오른쪽 입력 슬롯에 있는 아이템을 가져옵니다.

    setSecondItem(ItemStack secondItem): 오른쪽 입력 슬롯에 아이템을 설정합니다.

    getResult(): 결과 슬롯에 있는 아이템을 가져옵니다.

    setResult(ItemStack result): 결과 슬롯에 아이템을 설정합니다. 클라이언트가 입력 아이템과 일치하지 않으면 아이템을 꺼낼 수 없을 수 있습니다.

    canBypassCost(): 비용을 우회할 수 있는지 여부를 반환합니다.

    setBypassCost(boolean bypassCost): 비용 우회 여부를 설정합니다.

    canDoUnsafeEnchants(): 안전하지 않은 인챈트를 수행할 수 있는지 여부를 반환합니다.

    setDoUnsafeEnchants(boolean canDoUnsafeEnchants): 안전하지 않은 인챈트 수행 여부를 설정합니다.
    */

    @EventHandler
    public synchronized void onAnvil(@NotNull AnvilUpdateResultEvent e) {
        e.getInventory().setMaximumRepairCost(1000);
    }
}
