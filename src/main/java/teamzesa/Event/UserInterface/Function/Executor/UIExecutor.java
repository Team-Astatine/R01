package teamzesa.Event.UserInterface.Function.Executor;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import teamzesa.Event.UserInterface.Enhance.EnhanceUIClickEvent;
import teamzesa.Event.UserInterface.GSit.GSitUIClickEvent;
import teamzesa.Event.UserInterface.Menu.MainMenuUIClickEvent;
import teamzesa.Event.UserInterface.Function.Interface.Type;
import teamzesa.Event.UserInterface.Function.Interface.UIHolder;
import teamzesa.Event.UserInterface.Function.Interface.UIType;
import teamzesa.Event.UserInterface.TPA.TpaUIClickEvent;

/**
 * {@link Type} 각 UI 타입에 맞는 {@link org.bukkit.inventory.Inventory}를 생성하는 클래스입니다.
 * {@link UIHolder} 를 구현한 클래스에 {@link UIType} 어노테이션을 붙여서 구현합니다.
 */
public class UIExecutor {

    public UIExecutor(InventoryClickEvent event) {

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
            case TPA        -> new TpaUIClickEvent(event);
        }

    }
}
