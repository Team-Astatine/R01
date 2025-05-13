package teamzesa.Util.UserUIGenerator.Executor;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import teamzesa.Event.Enhance.PlayerInteraction.UserInterface.EnhanceUIClickEvent;
import teamzesa.Event.PlayerInteraction.UserInterface.GSIT.GSitUIClickEvent;
import teamzesa.Event.PlayerInteraction.UserInterface.Menu.MainMenuUIClickEvent;
import teamzesa.Util.UserUIGenerator.Interface.Type;
import teamzesa.Util.UserUIGenerator.Interface.UIHolder;
import teamzesa.Util.UserUIGenerator.Interface.UIType;

/**
 * {@link Type} 각 UI 타입에 맞는 {@link org.bukkit.inventory.Inventory}를 생성하는 클래스입니다.
 * {@link UIHolder} 를 구현한 클래스에 {@link UIType} 어노테이션을 붙여서 구현합니다.
 */
public class UIExecutor {

    private final InventoryClickEvent event;

    public UIExecutor(InventoryClickEvent event) {
        this.event = event;

        if (!(event.getView().getTopInventory().getHolder() instanceof UIHolder holder))
            return;

        if (ObjectUtils.isEmpty(holder.getClass().getAnnotation(UIType.class)))
            return;

//        System.out.println("Annotations: " +
//                Arrays.toString(holder.getClass().getDeclaredAnnotations()));

        Type type = holder.getClass().getAnnotation(UIType.class).value();
        switch (type) {
            case MAIN_MENU  -> new MainMenuUIClickEvent(event);
            case GSIT       -> new GSitUIClickEvent(event);
            case ENHANCE    -> new EnhanceUIClickEvent(event);
        }

    }
}
